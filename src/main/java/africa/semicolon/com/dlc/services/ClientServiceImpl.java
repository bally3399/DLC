package africa.semicolon.com.dlc.services;

import africa.semicolon.com.dlc.data.model.Admin;
import africa.semicolon.com.dlc.data.model.Client;
import africa.semicolon.com.dlc.data.model.Product;
import africa.semicolon.com.dlc.data.model.ShoppingCart;
import africa.semicolon.com.dlc.data.repository.ClientRepository;
import africa.semicolon.com.dlc.data.repository.ProductRepository;
import africa.semicolon.com.dlc.data.repository.ShoppingCartRepository;
import africa.semicolon.com.dlc.dtos.request.AddProductToShoppingCartRequest;
import africa.semicolon.com.dlc.dtos.request.LoginRequest;
import africa.semicolon.com.dlc.dtos.request.RegisterRequest;
import africa.semicolon.com.dlc.dtos.response.AddProductToShoppingCartResponse;
import africa.semicolon.com.dlc.dtos.response.LoginResponse;
import africa.semicolon.com.dlc.dtos.response.RegisterResponse;
import africa.semicolon.com.dlc.exceptions.IncorrectPasswordException;
import africa.semicolon.com.dlc.exceptions.InvalidCredentialException;
import africa.semicolon.com.dlc.exceptions.UserAlreadyExistException;
import africa.semicolon.com.dlc.utils.JwtUtils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;
    private ProductRepository productRepository;
    private final ShoppingCartRepository cartRepository;
    private ProductService productService;
    private ModelMapper modelMapper;
    @Override
    public RegisterResponse register(RegisterRequest request) {
        String email = request.getEmail();
        validate(email);
        validateRegistration(request);
        Client client = modelMapper.map(request, Client.class);
        client =clientRepository.save(client);
        var response = modelMapper.map(client, RegisterResponse.class);
        response.setMessage("Client registered successfully");
        return response;
    }

    @Override
    public void deleteAll() {
        clientRepository.deleteAll();
    }

    @Override
    public AddProductToShoppingCartResponse addProductToCart(AddProductToShoppingCartRequest request) {
        Client client = findClientById(request.getClientId());
        Product product = findProductById(request.getProductId());
        if (client.getCart() == null) {
            client.setCart(new ShoppingCart());
        }

        List<Product> products = client.getCart().getProducts();
        products.add(product);
        clientRepository.save(client);
        AddProductToShoppingCartResponse response = new AddProductToShoppingCartResponse();
        response.setMessage("Product added successfully");
        response.setShoppingCart(client.getCart());

        return response;
    }


    private void validate(String email) {
        for (Client user : clientRepository.findAll()) {
            if (user.getEmail().equals(email)) {
                throw new UserAlreadyExistException("email already exist");
            }
        }
    }

    private static void validateRegistration(RegisterRequest request) {
        if (!request.getEmail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"))
            throw new UserAlreadyExistException("Invalid Input");
        if (request.getPassword().isEmpty())
            throw new IncorrectPasswordException("Invalid Password provide a Password");
    }

    @Override
    public Client findClientById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(()-> new UserAlreadyExistException("Client already exist"));
    }

    @Override
    public Product findProductById(Long id) {
        return productService.findById(id);
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        return checkLoginDetail(email, password);
    }

    private LoginResponse checkLoginDetail(String email, String password) {
        Optional<Client> optionalUser = clientRepository.findByEmail(email);
        if (optionalUser.isPresent()){
            Client client = optionalUser.get();
            if (client.getPassword().equals(password)) {
                return loginResponseMapper(client);
            } else {
                throw new InvalidCredentialException("Invalid username or password");
            }
        } else {
            throw new InvalidCredentialException("Invalid username or password");
        }
    }

    private LoginResponse loginResponseMapper(Client user) {
        LoginResponse loginResponse = new LoginResponse();
        String accessToken = JwtUtils.generateAccessToken(user.getId());
        BeanUtils.copyProperties(user, loginResponse);
        loginResponse.setJwtToken(accessToken);
        loginResponse.setMessage("Login Successful");
        return loginResponse;
    }

}

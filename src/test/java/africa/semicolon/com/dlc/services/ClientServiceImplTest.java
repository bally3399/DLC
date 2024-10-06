package africa.semicolon.com.dlc.services;

import africa.semicolon.com.dlc.data.model.Client;
import africa.semicolon.com.dlc.data.model.Product;
import africa.semicolon.com.dlc.data.model.ProductCategory;
import africa.semicolon.com.dlc.data.model.ShoppingCart;
import africa.semicolon.com.dlc.data.repository.ShoppingCartRepository;
import africa.semicolon.com.dlc.dtos.request.AddProductToShoppingCartRequest;
import africa.semicolon.com.dlc.dtos.request.RegisterRequest;
import africa.semicolon.com.dlc.dtos.response.AddProductRequest;
import africa.semicolon.com.dlc.dtos.response.AddProductResponse;
import africa.semicolon.com.dlc.dtos.response.AddProductToShoppingCartResponse;
import africa.semicolon.com.dlc.dtos.response.RegisterResponse;
import africa.semicolon.com.dlc.exceptions.UserAlreadyExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ClientServiceImplTest {
    @Autowired
    private ClientService clientService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ShoppingCartRepository repository;
    private RegisterResponse registerResponse;
    private AddProductResponse addProductResponse;
    private AddProductResponse addProductResponse2;
    private AddProductToShoppingCartResponse addProductToShoppingCartResponse;
    @BeforeEach
    public void setUp() {
        clientService.deleteAll();
        RegisterRequest request = new RegisterRequest();
        request.setFirstName("bimbim");
        request.setLastName("bim");
        request.setEmail("bimbim@test.com");
        request.setPassword("1234");
        registerResponse = clientService.register(request);

        productService.deleteAll();
        AddProductRequest addProductRequest = new AddProductRequest();
        addProductRequest.setProductName("Italian Shoe");
        addProductRequest.setProductPrice(new BigDecimal("7000"));
        addProductRequest.setProductCategory(ProductCategory.SHOE);
        addProductRequest.setProductDescription("Male Italian shoe");
        addProductResponse = productService.add(addProductRequest);

        AddProductRequest addProductRequest2 = new AddProductRequest();
        addProductRequest2.setProductName("Italian Cloth");
        addProductRequest2.setProductPrice(new BigDecimal("9000"));
        addProductRequest2.setProductCategory(ProductCategory.CLOTHING);
        addProductRequest2.setProductDescription("Male Italian Cloth");
        addProductResponse2 = productService.add(addProductRequest2);

    }

    @Test
    public void registerClientTest() {
        assertThat(registerResponse).isNotNull();
        assertThat(registerResponse.getMessage()).isEqualTo("Client registered successfully");
    }


    @Test
    public void testThatClientCannotRegisterTwice() {
        clientService.deleteAll();
        RegisterRequest request = new RegisterRequest();
        request.setFirstName("bimbim");
        request.setLastName("bim");
        request.setEmail("bimbim@test.com");
        request.setPassword("1234");
        registerResponse = clientService.register(request);
        assertThrows(UserAlreadyExistException.class, () -> clientService.register(request));
    }

    @Test
    public void addProductToClientShoppingCartTest() {
        RegisterResponse response = registerResponse;
        assertThat(response).isNotNull();
        assertThat(response.getMessage()).isEqualTo("Client registered successfully");
        AddProductToShoppingCartRequest request = new AddProductToShoppingCartRequest();
        request.setProductId(addProductResponse.getProduct().getProductId());
        request.setClientId(response.getClient().getId());
        AddProductToShoppingCartResponse addProductToShoppingCartResponse = clientService.addProductToCart(request);
        assertThat(addProductToShoppingCartResponse).isNotNull();
        assertFalse(addProductToShoppingCartResponse.getShoppingCart().getProducts().isEmpty());
        assertThat(addProductToShoppingCartResponse.getShoppingCart().getProducts().size()).isEqualTo(1);
        assertThat(addProductToShoppingCartResponse.getMessage()).isEqualTo("Product added successfully");


    }
    @Test
    public void addTwoProductToClientShoppingCartTest1() {
        RegisterResponse response = registerResponse;
        AddProductToShoppingCartRequest request = new AddProductToShoppingCartRequest();
        request.setProductId(addProductResponse.getProduct().getProductId());
        request.setClientId(response.getClient().getId());
        AddProductToShoppingCartResponse addProductToShoppingCartResponse = clientService.addProductToCart(request);
        Client foundClient = clientService.findClientById(response.getClient().getId());
        assertThat(addProductToShoppingCartResponse).isNotNull();
        assertThat(foundClient.getCart().getProducts().size()).isEqualTo(1);
        //        assertThat(addProductToShoppingCartResponse.getShoppingCart().getProducts().size()).isEqualTo(1);

        AddProductToShoppingCartRequest request2 = new AddProductToShoppingCartRequest();
        request2.setProductId(addProductResponse2.getProduct().getProductId());
        request2.setClientId(response.getClient().getId());
        AddProductToShoppingCartResponse addProductToShoppingCartResponse1 = clientService.addProductToCart(request2);

//        assertThat(addProductToShoppingCartResponse).isNotNull();
//        assertThat(addProductToShoppingCartResponse1.getShoppingCart().getProducts().size()).isEqualTo(2);
//        assertThat(addProductToShoppingCartResponse.getMessage()).isEqualTo("Product added successfully");

    }

    @Test
    public void addTwoProductsToClientShoppingCartTest() {
        // Step 1: Register the client
        RegisterResponse response = registerResponse; // Assuming registerResponse is already set in the @BeforeEach setup
        assertThat(response).isNotNull();
        assertThat(response.getMessage()).isEqualTo("Client registered successfully");

        // Step 2: Add the first product to the cart
        AddProductToShoppingCartRequest firstProductRequest = new AddProductToShoppingCartRequest();
        firstProductRequest.setProductId(addProductResponse.getProduct().getProductId()); // Assuming addProductResponse is already set in the @BeforeEach setup
        firstProductRequest.setClientId(response.getClient().getId());

        AddProductToShoppingCartResponse addFirstProductResponse = clientService.addProductToCart(firstProductRequest);
        assertThat(addFirstProductResponse).isNotNull();
        assertFalse(addFirstProductResponse.getShoppingCart().getProducts().isEmpty());
        assertThat(addFirstProductResponse.getShoppingCart().getProducts().size()).isEqualTo(1);
        assertThat(addFirstProductResponse.getMessage()).isEqualTo("Product added successfully");

        // Step 3: Add a second product to the cart
        AddProductRequest secondProductRequest = new AddProductRequest();
        secondProductRequest.setProductName("Second Product");
        secondProductRequest.setProductPrice(new BigDecimal("10000"));
        secondProductRequest.setProductCategory(ProductCategory.SHOE);
        secondProductRequest.setProductDescription("High-end electronics");

        AddProductResponse secondProductResponse = productService.add(secondProductRequest);

        AddProductToShoppingCartRequest secondProductCartRequest = new AddProductToShoppingCartRequest();
        secondProductCartRequest.setProductId(secondProductResponse.getProduct().getProductId());
        secondProductCartRequest.setClientId(response.getClient().getId());

        AddProductToShoppingCartResponse addSecondProductResponse = clientService.addProductToCart(secondProductCartRequest);
        assertThat(addSecondProductResponse).isNotNull();
        assertThat(addSecondProductResponse.getShoppingCart().getProducts().size()).isEqualTo(2);
        assertThat(addSecondProductResponse.getMessage()).isEqualTo("Product added successfully");

        // Step 4: Final assertions to check if both products are in the cart
        assertThat(addSecondProductResponse.getShoppingCart().getProducts()).hasSize(2);
        assertThat(addSecondProductResponse.getShoppingCart().getProducts().get(0).getProductName()).isEqualTo("Italian Shoe");
        assertThat(addSecondProductResponse.getShoppingCart().getProducts().get(1).getProductName()).isEqualTo("Second Product");
    }

}
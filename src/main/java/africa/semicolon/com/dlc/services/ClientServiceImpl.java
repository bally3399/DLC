package africa.semicolon.com.dlc.services;

import africa.semicolon.com.dlc.data.model.Client;
import africa.semicolon.com.dlc.data.repository.ClientRepository;
import africa.semicolon.com.dlc.dtos.request.RegisterRequest;
import africa.semicolon.com.dlc.dtos.response.RegisterResponse;
import africa.semicolon.com.dlc.exceptions.IncorrectPasswordException;
import africa.semicolon.com.dlc.exceptions.UserAlreadyExistException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;
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

    private void validate(String email) {
        for (Client user : clientRepository.findAll()) {
            if (user.getEmail().equals(email.toLowerCase())) {
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


}

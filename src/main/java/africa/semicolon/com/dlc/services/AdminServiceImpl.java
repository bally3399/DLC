package africa.semicolon.com.dlc.services;

import africa.semicolon.com.dlc.data.model.Admin;
import africa.semicolon.com.dlc.data.model.Client;
import africa.semicolon.com.dlc.data.repository.AdminRepository;
import africa.semicolon.com.dlc.dtos.request.RegisterRequest;
import africa.semicolon.com.dlc.dtos.response.RegisterAdminResponse;
import africa.semicolon.com.dlc.dtos.response.RegisterResponse;
import africa.semicolon.com.dlc.exceptions.IncorrectPasswordException;
import africa.semicolon.com.dlc.exceptions.UserAlreadyExistException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService{
    private final AdminRepository adminRepository;
    private final ModelMapper modelMapper;

    @Override
    public RegisterAdminResponse registerAdmin(RegisterRequest registerRequest) {
        String email = registerRequest.getEmail();
        validate(email);
        validateRegistration(registerRequest);
        Admin admin = modelMapper.map(registerRequest, Admin.class);
        admin =adminRepository.save(admin);
        var response = modelMapper.map(admin, RegisterAdminResponse.class);
        response.setMessage("Admin registered successfully");
        return response;
    }
    private void validate(String email) {
        for (Admin user : adminRepository.findAll()) {
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

}

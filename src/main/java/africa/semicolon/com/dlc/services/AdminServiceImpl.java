package africa.semicolon.com.dlc.services;

import africa.semicolon.com.dlc.data.model.Admin;
import africa.semicolon.com.dlc.data.repository.AdminRepository;
import africa.semicolon.com.dlc.dtos.request.LoginRequest;
import africa.semicolon.com.dlc.dtos.request.RegisterRequest;
import africa.semicolon.com.dlc.dtos.response.LoginResponse;
import africa.semicolon.com.dlc.dtos.response.RegisterAdminResponse;
import africa.semicolon.com.dlc.exceptions.IncorrectPasswordException;
import africa.semicolon.com.dlc.exceptions.InvalidCredentialException;
import africa.semicolon.com.dlc.exceptions.UserAlreadyExistException;
import africa.semicolon.com.dlc.utils.JwtUtils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        return checkLoginDetail(email, password);
    }

    private LoginResponse checkLoginDetail(String email, String password) {
        Optional<Admin> optionalUser = adminRepository.findByEmail(email);
        if (optionalUser.isPresent()){
            Admin admin = optionalUser.get();
            if (admin.getPassword().equals(password)) {
                return loginResponseMapper(admin);
            } else {
                throw new InvalidCredentialException("Invalid username or password");
            }
        } else {
            throw new InvalidCredentialException("Invalid username or password");
        }
    }

    private LoginResponse loginResponseMapper(Admin user) {
        LoginResponse loginResponse = new LoginResponse();
        String accessToken = JwtUtils.generateAccessToken(user.getId());
        BeanUtils.copyProperties(user, loginResponse);
        loginResponse.setJwtToken(accessToken);
        loginResponse.setMessage("Login Successful");
        return loginResponse;
    }

}

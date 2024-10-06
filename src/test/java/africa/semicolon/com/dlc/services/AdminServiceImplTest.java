package africa.semicolon.com.dlc.services;

import africa.semicolon.com.dlc.dtos.request.LoginRequest;
import africa.semicolon.com.dlc.dtos.request.RegisterRequest;
import africa.semicolon.com.dlc.dtos.response.LoginResponse;
import africa.semicolon.com.dlc.dtos.response.RegisterAdminResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
public class AdminServiceImplTest {
    @Autowired
    private AdminService adminService;
    private RegisterAdminResponse registerAdminResponse;

    @BeforeEach
    public void setAdminUp(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("bally@gmail.com");
        registerRequest.setPassword("password");
        registerRequest.setFirstName("Bally");
        registerRequest.setLastName("Golden");
        registerAdminResponse = adminService.registerAdmin(registerRequest);
    }
    @Test
    public void registerAdminTest(){
        assertThat(registerAdminResponse).isNotNull();
        assertThat(registerAdminResponse.getMessage()).isEqualTo("Admin registered successfully");
    }

    @Test
    public void testAdminCanLogin(){
        RegisterAdminResponse adminResponse = registerAdminResponse;
        assertThat(adminResponse).isNotNull();

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("bally@gmail.com");
        loginRequest.setPassword("password");
        LoginResponse loginResponse = adminService.login(loginRequest);
        assertThat(loginResponse).isNotNull();
        assertThat(loginResponse.getMessage()).isEqualTo("Login Successful");
    }
}


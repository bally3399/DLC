package africa.semicolon.com.dlc.services;

import africa.semicolon.com.dlc.dtos.request.RegisterRequest;
import africa.semicolon.com.dlc.dtos.response.RegisterResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ClientServiceImplTest {
    @Autowired
    private ClientService clientService;

    @Test
    public void registerClientTest() {
        RegisterRequest request = new RegisterRequest();
        request.setFirstName("bimbim");
        request.setLastName("bim");
        request.setEmail("bimbim@test.com");
        request.setPassword("1234");
        RegisterResponse response = clientService.register(request);
        assertThat(response).isNotNull();
        assertThat(response.getMessage()).isEqualTo("Client registered successfully");
    }


}
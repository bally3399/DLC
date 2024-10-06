package africa.semicolon.com.dlc.services;

import africa.semicolon.com.dlc.dtos.request.AddProductToShoppingCartRequest;
import africa.semicolon.com.dlc.dtos.request.RegisterRequest;
import africa.semicolon.com.dlc.dtos.response.RegisterResponse;
import africa.semicolon.com.dlc.exceptions.UserAlreadyExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ClientServiceImplTest {
    @Autowired
    private ClientService clientService;
    private RegisterResponse registerResponse;
    @BeforeEach
    public void setUp() {
        clientService.deleteAll();
        RegisterRequest request = new RegisterRequest();
        request.setFirstName("bimbim");
        request.setLastName("bim");
        request.setEmail("bimbim@test.com");
        request.setPassword("1234");
        registerResponse = clientService.register(request);
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
        request.setShoppingCartId(100L);
        request.setClientId(200L);
        clientService.addingProductToCart(request);

    }
}
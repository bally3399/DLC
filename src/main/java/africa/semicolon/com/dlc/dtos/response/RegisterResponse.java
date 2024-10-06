package africa.semicolon.com.dlc.dtos.response;

import africa.semicolon.com.dlc.data.model.Client;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterResponse {
    private String message;
    private Client client;
}

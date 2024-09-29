package africa.semicolon.com.dlc.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterResponse {
    private String message;
    private String firstName;
    private String lastName;
    private String email;
}

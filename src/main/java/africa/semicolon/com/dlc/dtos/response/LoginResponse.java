package africa.semicolon.com.dlc.dtos.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginResponse {
    private String message;
    private String jwtToken;
    private String firstName;
    private String lastName;

}
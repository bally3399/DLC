package africa.semicolon.com.dlc.dtos.response;

import africa.semicolon.com.dlc.data.model.Admin;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterAdminResponse {
    private String message;
    private Admin admin;
}

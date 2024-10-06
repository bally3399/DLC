package africa.semicolon.com.dlc.services;

import africa.semicolon.com.dlc.dtos.request.RegisterRequest;
import africa.semicolon.com.dlc.dtos.response.RegisterAdminResponse;

public interface AdminService {

    RegisterAdminResponse registerAdmin(RegisterRequest registerRequest);
}

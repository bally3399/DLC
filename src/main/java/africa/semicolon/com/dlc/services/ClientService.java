package africa.semicolon.com.dlc.services;

import africa.semicolon.com.dlc.dtos.request.RegisterRequest;
import africa.semicolon.com.dlc.dtos.response.RegisterResponse;

public interface ClientService {
    RegisterResponse register(RegisterRequest request);

    void deleteAll();
}

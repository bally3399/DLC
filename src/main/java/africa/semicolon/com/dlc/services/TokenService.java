package africa.semicolon.com.dlc.services;

import africa.semicolon.com.dlc.data.model.Token;

public interface TokenService {
    String  createToken(String email);

    Token findByUserEmail(String email);

    void deleteToken(Long id);

}

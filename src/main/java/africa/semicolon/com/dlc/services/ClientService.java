package africa.semicolon.com.dlc.services;

import africa.semicolon.com.dlc.data.model.Client;
import africa.semicolon.com.dlc.data.model.Product;
import africa.semicolon.com.dlc.dtos.request.AddProductToShoppingCartRequest;
import africa.semicolon.com.dlc.dtos.request.RegisterRequest;
import africa.semicolon.com.dlc.dtos.response.RegisterResponse;

public interface ClientService {
    RegisterResponse register(RegisterRequest request);

    void deleteAll();

    void addingProductToCart(AddProductToShoppingCartRequest request);

    Client findClientById(Long id);

    Product findProductById(Long id);
}

package africa.semicolon.com.dlc.services;

import africa.semicolon.com.dlc.data.model.Product;
import africa.semicolon.com.dlc.dtos.response.AddProductRequest;
import africa.semicolon.com.dlc.dtos.response.AddProductResponse;

import java.util.List;

public interface ProductService {
    AddProductResponse add(AddProductRequest addProductRequest);

    void deleteAll();
    Product findById(Long productId);

    List<Product> findAll();
}

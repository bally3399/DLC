package africa.semicolon.com.dlc.services;

import africa.semicolon.com.dlc.data.model.Product;
import africa.semicolon.com.dlc.data.model.ProductCategory;
import africa.semicolon.com.dlc.dtos.request.AddProductRequest;
import africa.semicolon.com.dlc.dtos.request.UpdateProductRequest;
import africa.semicolon.com.dlc.dtos.response.AddProductResponse;
import africa.semicolon.com.dlc.dtos.response.DeleteProductResponse;
import africa.semicolon.com.dlc.dtos.response.UpdateProductResponse;

import java.util.List;

public interface ProductService {
    AddProductResponse add(AddProductRequest addProductRequest);

    void deleteAll();
    Product findById(Long productId);

    List<Product> findAll();

    UpdateProductResponse update(Long productId, UpdateProductRequest updateProductRequest);

    List<Product> findByCategory(ProductCategory productCategory);

    DeleteProductResponse deleteById(Long productId);
}

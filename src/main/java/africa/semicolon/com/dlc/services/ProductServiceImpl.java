package africa.semicolon.com.dlc.services;

import africa.semicolon.com.dlc.data.model.Product;
import africa.semicolon.com.dlc.data.model.ProductCategory;
import africa.semicolon.com.dlc.data.repository.ProductRepository;
import africa.semicolon.com.dlc.dtos.request.AddProductRequest;
import africa.semicolon.com.dlc.dtos.request.UpdateProductRequest;
import africa.semicolon.com.dlc.dtos.response.AddProductResponse;
import africa.semicolon.com.dlc.dtos.response.DeleteProductResponse;
import africa.semicolon.com.dlc.dtos.response.UpdateProductResponse;
import africa.semicolon.com.dlc.exceptions.DlcExceptions;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    public AddProductResponse add(AddProductRequest addProductRequest) {
        Product newProduct = modelMapper.map(addProductRequest, Product.class);
        Product savedProduct = productRepository.save(newProduct);
        AddProductResponse response = modelMapper.map(savedProduct, AddProductResponse.class);
        response.setMessage("Product added successfully");
        return response;
    }

    @Override
    public void deleteAll() {
        productRepository.deleteAll();
    }

    @Override
    public Product findById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(()->new DlcExceptions("Product not found"));
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public UpdateProductResponse update(Long productId, UpdateProductRequest updateProductRequest) {
        Product existingProduct = findById(productId);
        updateIfPresent(updateProductRequest.getName(), existingProduct::setProductName);
        updateIfPresent(updateProductRequest.getPrice(), existingProduct::setProductPrice);
        updateIfPresent(updateProductRequest.getDescription(), existingProduct::setProductDescription);
        updateIfPresent(updateProductRequest.getCategory(), existingProduct::setProductCategory);
        productRepository.save(existingProduct);
        UpdateProductResponse response = modelMapper.map(existingProduct, UpdateProductResponse.class);
        response.setMessage("Product updated successfully");
        return response;
    }

    @Override
    public List<Product> findByCategory(ProductCategory productCategory) {
        List<Product> productByCategory = new ArrayList<>();
        List<Product> productList = productRepository.findAll();
        for (Product product : productList) {
            if (product.getProductCategory().equals(productCategory)){
                productByCategory.add(product);
            }
        }
        return productByCategory;
    }
    @Override
    public DeleteProductResponse deleteById(Long productId) {
        Product product = findById(productId);
        productRepository.delete(product);
        DeleteProductResponse response = new DeleteProductResponse();
        response.setMessage("Product deleted successfully");
        return response;
    }
    private <T> void updateIfPresent(T value, Consumer<T> setter) {
        Optional.ofNullable(value)
                .filter(v -> !(v instanceof String) || !((String) v).trim().isEmpty())
                .ifPresent(setter);
    }

}

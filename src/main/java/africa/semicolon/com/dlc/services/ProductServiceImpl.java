package africa.semicolon.com.dlc.services;

import africa.semicolon.com.dlc.data.model.Product;
import africa.semicolon.com.dlc.data.repository.ProductRepository;
import africa.semicolon.com.dlc.dtos.response.AddProductRequest;
import africa.semicolon.com.dlc.dtos.response.AddProductResponse;
import africa.semicolon.com.dlc.exceptions.DlcExceptions;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

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
}

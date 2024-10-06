package africa.semicolon.com.dlc.services;

import africa.semicolon.com.dlc.data.model.Product;
import africa.semicolon.com.dlc.data.model.ProductCategory;
import africa.semicolon.com.dlc.dtos.response.AddProductRequest;
import africa.semicolon.com.dlc.dtos.response.AddProductResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;
    private AddProductResponse addProductResponse;

    private Product product;

    @BeforeEach
    public void setUp() {
        productService.deleteAll();
        AddProductRequest addProductRequest = new AddProductRequest();
        addProductRequest.setProductName("Italian Shoe");
        addProductRequest.setProductPrice(new BigDecimal("7000"));
        addProductRequest.setProductCategory(ProductCategory.SHOE);
        addProductRequest.setProductDescription("Male Italian shoe");
        addProductResponse = productService.add(addProductRequest);
    }

    @Test
    public void testAddProduct(){
        assertThat(addProductResponse).isNotNull();
        assertThat(addProductResponse.getMessage()).isEqualTo("Product added successfully");
    }

    @Test
    public void testFindProduct() {
        Product foundProduct = productService.findById(addProductResponse.getProduct().getProductId());
        assertThat(foundProduct).isNotNull();
        assertThat(foundProduct.getProductName()).isEqualTo("Italian Shoe");
    }

    @Test
    public void testFindAllProduct(){
        List<Product> products = productService.findAll();
        assertThat(products.size()).isGreaterThan(0);
    }

    @AfterEach
    public void tearDown() {
        productService.deleteAll();
    }
}

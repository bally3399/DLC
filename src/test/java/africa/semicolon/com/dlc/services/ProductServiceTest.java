package africa.semicolon.com.dlc.services;

import africa.semicolon.com.dlc.data.model.Product;
import africa.semicolon.com.dlc.data.model.ProductCategory;
import africa.semicolon.com.dlc.dtos.request.UpdateProductRequest;
import africa.semicolon.com.dlc.dtos.request.AddProductRequest;
import africa.semicolon.com.dlc.dtos.response.AddProductResponse;
import africa.semicolon.com.dlc.dtos.response.DeleteProductResponse;
import africa.semicolon.com.dlc.dtos.response.UpdateProductResponse;
import africa.semicolon.com.dlc.exceptions.DlcExceptions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    public void testFindProductByCategory(){
        List<Product> products = productService.findByCategory(ProductCategory.SHOE);
        assertThat(products.size()).isGreaterThan(0);
    }

    @Test
    public void testFindProductWithWrongId(){
        assertThrows(DlcExceptions.class, ()->productService.findById(1000000L));
    }

    @Test
    public void testFindAllProduct(){
        List<Product> products = productService.findAll();
        assertThat(products.size()).isGreaterThan(0);
    }

    @Test
    public void testUpdateProduct(){
        assertThat(addProductResponse.getProduct().getProductName()).isEqualTo("Italian Shoe");
        assertThat(addProductResponse.getProduct().getProductPrice().compareTo(new BigDecimal("7000"))).isEqualTo(0);
        UpdateProductRequest updateProductRequest = new UpdateProductRequest();
        updateProductRequest.setName("Updated Italian Shoe");
        updateProductRequest.setPrice(new BigDecimal("12000"));
        updateProductRequest.setDescription("Male Italian shoe updated");
        UpdateProductResponse response = productService.update(addProductResponse.getProduct().getProductId(), updateProductRequest);
        assertThat(response).isNotNull();
        Product updatedProduct = productService.findById(addProductResponse.getProduct().getProductId());
        assertThat(updatedProduct.getProductName()).isEqualTo("Updated Italian Shoe");
        assertThat(updatedProduct.getProductPrice().compareTo(new BigDecimal("12000"))).isEqualTo(0);
    }

    @Test
    public void testUpdateProductWithWrongId(){
        UpdateProductRequest updateProductRequest = new UpdateProductRequest();
        updateProductRequest.setName("Updated Italian Shoe");
        updateProductRequest.setPrice(new BigDecimal("12000"));
        updateProductRequest.setDescription("Male Italian shoe updated");
        assertThrows(DlcExceptions.class, ()-> productService.update(1000000L, updateProductRequest));
    }

    @Test
    public void testDeleteProduct(){
        int productsLength = productService.findAll().size();
        List<Product> productList = productService.findAll();
        assertThat(productList.size()).isEqualTo(productsLength);
        DeleteProductResponse response = productService.deleteById(addProductResponse.getProduct().getProductId());
        assertThat(response).isNotNull();
        assertThrows(DlcExceptions.class, ()->productService.findById(addProductResponse.getProduct().getProductId()));
//        int newProductsLength = productsLength - 1;
//        assertThat(productList.size()).isEqualTo(newProductsLength);
    }

    @Test
    public void testDeleteProductWithWrongId(){
        assertThrows(DlcExceptions.class, ()-> productService.deleteById(1000000L));
    }

    @Test
    public void testDeleteAllProduct(){
        List<Product> products = productService.findAll();
        assertThat(products.size()).isGreaterThan(0);
        productService.deleteAll();
        assertThat(productService.findAll().size()).isEqualTo(0);
    }

    @AfterEach
    public void tearDown() {
        productService.deleteAll();
    }

}

package africa.semicolon.com.dlc.dtos.response;

import africa.semicolon.com.dlc.data.model.Product;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateProductResponse {
    private String message;
    private Product product;
}

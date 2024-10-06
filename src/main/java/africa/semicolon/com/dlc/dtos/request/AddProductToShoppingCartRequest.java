package africa.semicolon.com.dlc.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddProductToShoppingCartRequest {
    private Long productId;
    private Long clientId;
}

package africa.semicolon.com.dlc.dtos.response;

import africa.semicolon.com.dlc.data.model.ShoppingCart;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddProductToShoppingCartResponse {
    private String message;
    private ShoppingCart shoppingCart;
}

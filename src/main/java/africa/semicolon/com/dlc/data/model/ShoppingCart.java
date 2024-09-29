package africa.semicolon.com.dlc.data.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ShoppingCart {
    private List<Item> items;
}

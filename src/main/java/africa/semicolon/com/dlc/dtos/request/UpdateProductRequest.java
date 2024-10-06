package africa.semicolon.com.dlc.dtos.request;

import africa.semicolon.com.dlc.data.model.ProductCategory;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class UpdateProductRequest {
    private String name;
    private String description;
    private ProductCategory category;
    private BigDecimal price;
}

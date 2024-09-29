package africa.semicolon.com.dlc.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Table(name = "items")
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantityOfProduct;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}

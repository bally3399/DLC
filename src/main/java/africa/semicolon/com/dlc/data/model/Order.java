package africa.semicolon.com.dlc.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Table(name = "orders")
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private String phoneNumber;
    @ManyToOne
    private Client client;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

}

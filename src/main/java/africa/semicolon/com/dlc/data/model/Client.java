package africa.semicolon.com.dlc.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String homeAddress;
    private String password;
    private String phoneNumber;
    @OneToMany
    @JoinColumn(name = "shoppingcart_id")
    private List<ShoppingCart> shoppingCartList = new ArrayList<>();

}

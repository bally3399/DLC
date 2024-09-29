package africa.semicolon.com.dlc.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Table(name = "card_information")
@Entity
public class CardInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String creditCardNumber;
    private String cardExpirationDate;
    private String cvv;
    private String cardName;
    @ManyToOne
    @JoinColumn(name = "card_id")
    private CardType cardType;

}

package africa.semicolon.com.dlc.data.model;

import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Table(name = "card_information")
public class CardInformation {
    private String creditCardNumber;
    private String cardExpirationDate;
    private String cvv;
    private String cardName;
    private CardType cardType;

}

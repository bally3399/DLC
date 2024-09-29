package africa.semicolon.com.dlc.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class BillingInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String phoneNumber;
    private String email;
    private String receiverName;
    private String deliveryAddress;
    private LocalDateTime deliveryDate;
    @ManyToOne
    @JoinColumn(name = "card_information_id")
    private CardInformation cardInformation;
}

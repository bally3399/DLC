package africa.semicolon.com.dlc.data.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class BillingInformation {
    private String phoneNumber;
    private String email;
    private String receiverName;
    private String deliveryAddress;
    private LocalDateTime deliveryDate;
    private CardInformation cardInformation;
}

package co.com.sofkoin.gamma.application.commons.views;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TransactionView extends View {
    private String transactionId;
    private String userId;
    private String type;
    private String CryptoSymbol;
    private Double cryptoAmount;
    private Double cryptoPrice;
    private LocalDateTime timestamp;
}

package co.com.sofkoin.gamma.application.commons.views;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OfferView extends View{
    private String offerId;
    private String publisherId;
    private String targetAudienceId;
    private String cryptoSymbol;
    private Double cryptoAmount;
    private Double cryptoPrice;
}

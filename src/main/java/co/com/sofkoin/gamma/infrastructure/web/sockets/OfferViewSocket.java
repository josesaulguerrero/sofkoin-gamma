package co.com.sofkoin.gamma.infrastructure.web.sockets;

import co.com.sofkoin.gamma.application.commons.views.OfferView;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class OfferViewSocket {

    private final SimpMessagingTemplate messagingTemplate;

    public void emitP2POfferPublished(OfferView offerView) {

        if (offerView.getTargetAudienceId().equals("-")) {
            this.messagingTemplate.convertAndSend("/topic/p2p.offer.published", offerView);
        } else {
            this.messagingTemplate
                    .convertAndSend(String.format("/topic/%s/p2p.offer.published", offerView.getTargetAudienceId()), offerView);
        }

    }

    public void emitP2POfferDeleted(OfferView offerView) {

        this.messagingTemplate.convertAndSend("/topic/p2p.offer.deleted", offerView);

    }

}

package co.com.sofkoin.gamma.infrastructure.web.sockets;

import co.com.sofkoin.gamma.application.commons.views.TransactionView;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class TransactionViewSocket {

    private final SimpMessagingTemplate messagingTemplate;

    public void emitP2PTransactionView (TransactionView transactionView){

        this.messagingTemplate.convertAndSend("/topic/p2p.transaction.committed", transactionView);

    }

}

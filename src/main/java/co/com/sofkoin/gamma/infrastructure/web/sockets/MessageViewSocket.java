package co.com.sofkoin.gamma.infrastructure.web.sockets;

import co.com.sofkoin.gamma.application.commons.views.MessageView;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class MessageViewSocket {

    private final SimpMessagingTemplate messagingTemplate;

    public void emitMessageSaved(MessageView messageView){

        this.messagingTemplate.convertAndSend(String.format("/topic/%s/message.saved", messageView.getReceiverId()), messageView);
        this.messagingTemplate.convertAndSend(String.format("/topic/%s/message.saved", messageView.getSenderId()), messageView);

    }

    public void emitMessageStatusChanged(MessageView messageView){

        this.messagingTemplate.convertAndSend(String.format("/topic/%s/message.status.changed", messageView.getReceiverId()), messageView);
        this.messagingTemplate.convertAndSend(String.format("/topic/%s/message.status.changed", messageView.getSenderId()), messageView);

    }


}

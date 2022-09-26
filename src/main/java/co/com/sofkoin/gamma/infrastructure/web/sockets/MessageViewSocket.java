package co.com.sofkoin.gamma.infrastructure.web.sockets;

import co.com.sofkoin.gamma.application.commons.views.MessageView;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class MessageViewSocket {

    private final SimpMessagingTemplate messagingTemplate;

    public void emitMessageSaved(MessageView messageView) {
        if (messageView.getMessageRelationType().equals("SENDER")) {
            this.messagingTemplate.convertAndSend(String.format("/topic/%s/message.saved", messageView.getSenderId()), messageView);
        } else {
            this.messagingTemplate.convertAndSend(String.format("/topic/%s/message.saved", messageView.getReceiverId()), messageView);
        }
    }

    public void emitMessageStatusChanged(MessageView messageView) {

        if (messageView.getMessageRelationType().equals("SENDER")) {
            this.messagingTemplate.convertAndSend(String.format("/topic/%s/message.status.changed", messageView.getSenderId()), messageView);
        } else {
            this.messagingTemplate.convertAndSend(String.format("/topic/%s/message.status.changed", messageView.getReceiverId()), messageView);
        }

    }

}

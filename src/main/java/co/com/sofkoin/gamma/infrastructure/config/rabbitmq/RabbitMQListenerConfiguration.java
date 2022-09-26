package co.com.sofkoin.gamma.infrastructure.config.rabbitmq;

import co.com.sofkoin.gamma.application.commons.views.MessageView;
import co.com.sofkoin.gamma.application.commons.views.OfferView;
import co.com.sofkoin.gamma.application.commons.views.TransactionView;
import co.com.sofkoin.gamma.infrastructure.commons.json.JSONMapper;
import co.com.sofkoin.gamma.infrastructure.web.sockets.MessageViewSocket;
import co.com.sofkoin.gamma.infrastructure.web.sockets.OfferViewSocket;
import co.com.sofkoin.gamma.infrastructure.web.sockets.TransactionViewSocket;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class RabbitMQListenerConfiguration {
    public static final String PROXY_QUEUE_P2P_OFFER_CREATED = "domain_views.p2p_offer_created";
    public static final String PROXY_QUEUE_P2P_OFFER_DELETED = "domain_views.p2p_offer_deleted";
    public static final String PROXY_QUEUE_MESSAGE_SAVED = "domain_views.message_saved";
    public static final String PROXY_QUEUE_MESSAGE_STATUS_CHANGED = "domain_views.message_status_changed";
    public static final String PROXY_QUEUE_P2P_TRANSACTION_COMMITTED = "domain_views.p2p_transaction_committed";

    private final JSONMapper jsonMapper;
    private final MessageViewSocket messageViewSocket;
    private final OfferViewSocket offerViewSocket;
    private final TransactionViewSocket transactionViewSocket;


    @RabbitListener(queues = {PROXY_QUEUE_P2P_OFFER_CREATED})
    public void p2pOfferCreatedListener(String jsonOfferView) {
        System.out.println("jsonOfferView = " + jsonOfferView);
        OfferView view = (OfferView) this.jsonMapper.readFromJson(jsonOfferView, OfferView.class);
        this.offerViewSocket.emitP2POfferPublished(view);
    }

    @RabbitListener(queues = {PROXY_QUEUE_P2P_OFFER_DELETED})
    public void p2pOfferDeletedListener(String jsonOfferView) {
        System.out.println("jsonOfferView = " + jsonOfferView);
        OfferView view = (OfferView) this.jsonMapper.readFromJson(jsonOfferView, OfferView.class);
        this.offerViewSocket.emitP2POfferDeleted(view);
    }

    @RabbitListener(queues = {PROXY_QUEUE_MESSAGE_SAVED})
    public void messageSavedListener(String jsonMessageView) {
        System.out.println("jsonMessageView = " + jsonMessageView);
        MessageView view = (MessageView) this.jsonMapper.readFromJson(jsonMessageView, MessageView.class);
        this.messageViewSocket.emitMessageSaved(view);
    }

    @RabbitListener(queues = {PROXY_QUEUE_MESSAGE_STATUS_CHANGED})
    public void messageStatusChangedListener(String jsonMessageView) {
        System.out.println("jsonMessageView = " + jsonMessageView);
        MessageView view = (MessageView) this.jsonMapper.readFromJson(jsonMessageView, MessageView.class);
        this.messageViewSocket.emitMessageStatusChanged(view);
    }

    @RabbitListener(queues = {PROXY_QUEUE_P2P_TRANSACTION_COMMITTED})
    public void p2pTransactionCommittedListener(String jsonTransactionView) {
        System.out.println("jsonTransactionView = " + jsonTransactionView);
        TransactionView view = (TransactionView) this.jsonMapper.readFromJson(jsonTransactionView, TransactionView.class);
        this.transactionViewSocket.emitP2PTransactionView(view);
    }

}

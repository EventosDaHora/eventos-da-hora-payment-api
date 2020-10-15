package com.eventosdahora.payment.ms.kafka;

import com.eventosdahora.payment.ms.dto.OrderDTO;
import com.eventosdahora.payment.ms.service.PaymentService;
import io.smallrye.mutiny.Uni;
import lombok.extern.java.Log;
import org.eclipse.microprofile.reactive.messaging.Acknowledgment;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Log
@ApplicationScoped
public class PagamentoKafkaHandler {
    
    @Inject
    PaymentService paymentService;
    
    @Incoming("payments")
    @Outgoing("envia-resposta")
    @Acknowledgment(Acknowledgment.Strategy.PRE_PROCESSING)
    public OrderDTO processor(OrderDTO orderDTO) throws Exception {
        log.info("Pagamento que chegou do tópico 'executa-pagamento': " + orderDTO);
    
        return paymentService.handleOrder(orderDTO);
    }
    
}

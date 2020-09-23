package com.eventosdahora.payment.ms.kafka;

import com.eventosdahora.payment.ms.dto.OrderDTO;
import lombok.extern.java.Log;
import org.eclipse.microprofile.reactive.messaging.Acknowledgment;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;

@Log
@ApplicationScoped
public class PagamentoKafkaHandler {

    @Incoming("tickets")
    @Outgoing("envia-resposta")
    @Acknowledgment(Acknowledgment.Strategy.PRE_PROCESSING)
    public OrderDTO processa(Message<OrderDTO> orderDTO) {
        OrderDTO payload = orderDTO.getPayload();
        log.info("Pedido que chegou do tópico: " + payload);


        payload.setOrderEvent(OrderEvent.PAGAR_TICKET_APROVADO);
        return payload;
    }

}

package com.eventosdahora.payment.ms.kafka;

import com.eventosdahora.payment.ms.dominio.Pedido;
import com.eventosdahora.payment.ms.dominio.PedidoEvent;
import lombok.extern.java.Log;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;

@Log
@ApplicationScoped
public class TicketKafka {

    @Incoming("tickets")
    @Outgoing("envia-resposta")
    public Pedido processa(Pedido pedido) {
        log.info("Pedido que chegou do tópico: " + pedido);
        pedido.setEvent(PedidoEvent.PAGAR_TICKET_APROVADO);
        return pedido;
    }
}

package com.eventosdahora.payment.ms.kafka;

import com.eventosdahora.payment.ms.dominio.Pedido;
import io.quarkus.kafka.client.serialization.JsonbDeserializer;

public class PedidoDeserializer extends JsonbDeserializer<Pedido> {

    public PedidoDeserializer() {
        super(Pedido.class);
    }

}

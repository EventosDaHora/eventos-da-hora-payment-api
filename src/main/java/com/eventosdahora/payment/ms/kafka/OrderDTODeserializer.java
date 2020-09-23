package com.eventosdahora.payment.ms.kafka;

import com.eventosdahora.payment.ms.dto.OrderDTO;
import io.quarkus.kafka.client.serialization.JsonbDeserializer;

public class OrderDTODeserializer extends JsonbDeserializer<OrderDTO> {

    public OrderDTODeserializer() {
        super(OrderDTO.class);
    }
}

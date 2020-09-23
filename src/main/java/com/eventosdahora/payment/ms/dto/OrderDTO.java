package com.eventosdahora.payment.ms.dto;

import com.eventosdahora.payment.ms.kafka.OrderEvent;
import com.eventosdahora.payment.ms.kafka.OrderState;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class OrderDTO {

    public static final String IDENTIFICADOR = "ID_PEDIDO";

    private Long orderId;

    private LocalDate createdDate;

    private OrderState orderState;

    private OrderEvent orderEvent;

    private Long paymentId;

    private BigDecimal fees;

    private Long userId;

    @Builder.Default
    private List<TicketDTO> tickets = new ArrayList<>();
}

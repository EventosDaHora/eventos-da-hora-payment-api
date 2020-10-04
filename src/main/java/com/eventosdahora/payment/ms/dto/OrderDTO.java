package com.eventosdahora.payment.ms.dto;

import com.eventosdahora.payment.ms.dominio.Payment;
import com.eventosdahora.payment.ms.kafka.OrderEvent;
import com.eventosdahora.payment.ms.kafka.OrderState;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

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
	
	private BigDecimal fees;
	
	private Long userId;
	
	private Payment payment;
	
}

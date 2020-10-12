package com.eventosdahora.payment.ms.dto;

import com.eventosdahora.payment.ms.dominio.PaymentType;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class PaymentDTO {
	
	public BigDecimal vlAmount;
	public PaymentType paymentType;
	public Long paymentId;
	
}

package com.eventosdahora.payment.ms.dto;

import com.eventosdahora.payment.ms.dominio.PaymentType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentDTO {
	
	public BigDecimal vlAmount;
	public PaymentType paymentType;
	public Long paymentId;
	
}

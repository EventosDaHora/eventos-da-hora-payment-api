package com.eventosdahora.payment.ms.dominio;

import lombok.Getter;

@Getter
public enum PaymentStatus {
	
	CRIADO, PAGO, PAGO_REJEITADO, CANCELADO;
}

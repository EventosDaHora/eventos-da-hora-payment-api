package com.eventosdahora.payment.ms.kafka;

public enum OrderEvent {
    PAGAR_TICKET,
    PAGAR_TICKET_APROVADO,
    PAGAR_TICKET_NEGADO,
    
    RESTAURAR_PAGAR_TICKET,
    RESTAURAR_PAGAR_TICKET_APROVADO,
    RESTAURAR_PAGAR_TICKET_NEGADO,
}

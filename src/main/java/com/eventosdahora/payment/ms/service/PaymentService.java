package com.eventosdahora.payment.ms.service;

import com.eventosdahora.payment.ms.dominio.Payment;
import com.eventosdahora.payment.ms.dominio.PaymentStatus;
import com.eventosdahora.payment.ms.dto.OrderDTO;
import com.eventosdahora.payment.ms.kafka.OrderEvent;
import io.smallrye.mutiny.Uni;
import lombok.extern.java.Log;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@Log
@ApplicationScoped
public class PaymentService {
	
	public Uni<OrderDTO> handleOrder(OrderDTO orderDTO) {
		if (OrderEvent.PAGAR_TICKET.equals(orderDTO.getOrderEvent())) {
			return Uni.createFrom().completionStage(CompletableFuture.supplyAsync(() -> pagarTicket(orderDTO)));
		}
		
		if (OrderEvent.RESTAURAR_PAGAR_TICKET.equals(orderDTO.getOrderEvent())) {
			return Uni.createFrom().completionStage(CompletableFuture.supplyAsync(() -> restaurarPagarTicket(orderDTO)));
		}
		
		return Uni.createFrom().failure(() -> new Exception("Estado inválido"));
	}
	
	@Transactional
	private OrderDTO restaurarPagarTicket(OrderDTO orderDTO) {
		try {
			orderDTO.getPayment().delete();
			orderDTO.setOrderEvent(OrderEvent.RESTAURAR_PAGAR_TICKET_APROVADO);
		} catch (PersistenceException pe) {
			orderDTO.setOrderEvent(OrderEvent.RESTAURAR_PAGAR_TICKET_NEGADO);
			return orderDTO;
		}
		return orderDTO;
	}
	
	@Transactional
	public OrderDTO pagarTicket(OrderDTO orderDTO) {
		System.out.println("---- Realizando Pagamento: ");
		System.out.println(orderDTO);
		PaymentStatus paymentTemp;
		
		if (verifyPayment(orderDTO)) {
			paymentTemp = PaymentStatus.PAGO;
			orderDTO.setOrderEvent(OrderEvent.PAGAR_TICKET_APROVADO);
			System.out.println("---- Pagamento APROVADO ");
		} else {
			paymentTemp = PaymentStatus.PAGO_REJEITADO;
			orderDTO.setOrderEvent(OrderEvent.PAGAR_TICKET_NEGADO);
			System.out.println("---- Pagamento REPROVADO ");
		}
		
		orderDTO.getPayment().setDtCreate(LocalDateTime.now());
		orderDTO.getPayment().setPaymentStatus(paymentTemp);
		if(paymentTemp.equals(PaymentStatus.PAGO)) {
			orderDTO.getPayment().setDtPayment(LocalDateTime.now());
		}
		orderDTO.getPayment().persist();
		
		return orderDTO;
	}
	
	private boolean verifyPayment(OrderDTO orderDTO) {
		// orderId par, pagamento aprovado
		// orderId impar, pagamento negado
		return orderDTO.getOrderId() % 2 == 0;
	}
}

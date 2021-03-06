package com.eventosdahora.payment.ms.service;

import com.eventosdahora.payment.ms.dominio.Payment;
import com.eventosdahora.payment.ms.dominio.PaymentStatus;
import com.eventosdahora.payment.ms.dto.OrderDTO;
import com.eventosdahora.payment.ms.kafka.OrderEvent;
import com.eventosdahora.payment.ms.repository.PaymentRepository;
import lombok.extern.java.Log;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Log
@ApplicationScoped
public class PaymentService {

	@Inject
	PaymentRepository paymentRepository;

	public OrderDTO handleOrder(OrderDTO orderDTO) throws Exception {
		if (OrderEvent.PAGAR_TICKET.equals(orderDTO.getOrderEvent())) {
			return pagarTicket(orderDTO);
		}

		if (OrderEvent.RESTAURAR_PAGAR_TICKET.equals(orderDTO.getOrderEvent())) {
			return restaurarPagarTicket(orderDTO);
		}

		throw new Exception("Estado inválido");
	}

	@Transactional
	private OrderDTO restaurarPagarTicket(OrderDTO orderDTO) {
		try {
			paymentRepository.deleteById(orderDTO.getPayment().getPaymentId());
			orderDTO.setOrderEvent(OrderEvent.RESTAURAR_PAGAR_TICKET_APROVADO);
		} catch (PersistenceException pe) {
			orderDTO.setOrderEvent(OrderEvent.RESTAURAR_PAGAR_TICKET_NEGADO);
			return orderDTO;
		}

		log.info(orderDTO.toString());
		return orderDTO;
	}

	@Transactional
	public OrderDTO pagarTicket(OrderDTO orderDTO) {
		PaymentStatus paymentStatus;
		Payment payment = new Payment(orderDTO.getPayment().getVlAmount(), orderDTO.getPayment().getPaymentType());

		if (verifyPayment(orderDTO)) {
			paymentStatus = PaymentStatus.PAGO;
			orderDTO.setOrderEvent(OrderEvent.PAGAR_TICKET_APROVADO);
			System.out.println("---- Pagamento APROVADO ");
		} else {
			paymentStatus = PaymentStatus.PAGO_REJEITADO;
			orderDTO.setOrderEvent(OrderEvent.PAGAR_TICKET_NEGADO);
			System.out.println("---- Pagamento REPROVADO ");
		}

		payment.setDtCreate(LocalDateTime.now());
		payment.setPaymentStatus(paymentStatus);
		if(PaymentStatus.PAGO.equals(paymentStatus)) {
			payment.setDtPayment(LocalDateTime.now());
		}

		paymentRepository.persistAndFlush(payment);

		orderDTO.getPayment().setPaymentId(payment.getId());
		log.info(orderDTO.toString());
		return orderDTO;
	}

	private boolean verifyPayment(OrderDTO orderDTO) {
		// orderId par, pagamento aprovado
		// orderId impar, pagamento negado
		return orderDTO.getOrderId() % 2 != 0;
	}
}

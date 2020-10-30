package com.eventosdahora.payment.ms.resource;

import com.eventosdahora.payment.ms.dominio.Payment;
import com.eventosdahora.payment.ms.repository.PaymentRepository;
import lombok.extern.java.Log;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Log
@Path("/payments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PaymentResource {
	
	@Inject
	PaymentRepository paymentRepository;
	
	@GET
	public Response getAll() {
		return Response.ok(paymentRepository.listAll()).build();
	}
	
}
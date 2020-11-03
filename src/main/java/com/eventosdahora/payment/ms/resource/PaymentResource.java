package com.eventosdahora.payment.ms.resource;

import com.eventosdahora.payment.ms.dominio.Payment;
import com.eventosdahora.payment.ms.repository.PaymentRepository;
import lombok.extern.java.Log;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Log
@Path("/payments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PaymentResource {
	
	@Inject
	PaymentRepository paymentRepository;
	
	@GET
	@Path("/{paymentId}")
	public Response getById(@PathParam("paymentId") Long paymentId) {
		Optional<Payment> byIdOptional = paymentRepository.findByIdOptional(paymentId);
		if(byIdOptional.isPresent())
			return  Response.ok(byIdOptional.get()).build();
		
		return Response.status(Response.Status.NOT_FOUND).build();
	}
	
	@GET
	public Response getAll() {
		return Response.ok(paymentRepository.listAll()).build();
	}
	
}

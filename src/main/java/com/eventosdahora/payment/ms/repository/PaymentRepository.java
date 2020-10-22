package com.eventosdahora.payment.ms.repository;

import com.eventosdahora.payment.ms.dominio.Payment;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PaymentRepository implements PanacheRepository<Payment> {

}

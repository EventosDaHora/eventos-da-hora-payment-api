package com.eventosdahora.payment.ms.dominio;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class PaymentType extends PanacheEntity {

    @Column(name = "id_payment_type")
    public Long id;

    @Column(name = "ds_payment_type")
    public String type;
}

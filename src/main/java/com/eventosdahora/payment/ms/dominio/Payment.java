package com.eventosdahora.payment.ms.dominio;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Payment extends PanacheEntity {

    @Column(name = "id_payment")
    public Long id;




}

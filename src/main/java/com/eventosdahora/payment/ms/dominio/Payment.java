package com.eventosdahora.payment.ms.dominio;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@Entity
@Data
public class Payment extends PanacheEntity {
    
    @Column(name = "id_payment", length = 19)
    public Long id;
    
    @Column(name = "dt_create")
    public LocalDateTime dtCreate;
    
    @Column(name = "dt_payment")
    public LocalDateTime dtPayment;
    
    @Column(name = "vl_amount")
    public BigDecimal vlAmount;
    
    @Column(name = "id_status_payment")
    public PaymentStatus paymentStatus;
    
    @Column(name = "id_payment_type")
    public PaymentType paymentType;
    
    public Payment() {
        this.paymentStatus = PaymentStatus.CRIADO;
    }
    

}

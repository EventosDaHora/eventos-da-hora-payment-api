package com.eventosdahora.payment.ms.dominio;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@Entity
@Data
public class Payment extends PanacheEntity {
    
    @Column(name = "id_payment", length = 19)
    public Long paymentId;
    
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
        this.paymentId = new Date().getTime();
        this.paymentStatus = PaymentStatus.CRIADO;
    }
    
    public Payment(BigDecimal vlAmount, PaymentType paymentType) {
        this.paymentId = new Date().getTime();
        this.paymentStatus = PaymentStatus.CRIADO;
        this.vlAmount = vlAmount;
        this.paymentType = paymentType;
    }
    

}

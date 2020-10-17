package com.eventosdahora.payment.ms.dominio;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@Entity
public class Payment extends PanacheEntity {
    
    @Column(name = "id_payment", length = 19)
    public Long id;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "dt_create")
    public LocalDateTime dtCreate;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "dt_payment")
    public LocalDateTime dtPayment;
    
    @Column(name = "vl_amount")
    public BigDecimal vlAmount;
    
    @Column(name = "id_status_payment")
    public PaymentStatus paymentStatus;
    
    @Column(name = "id_payment_type")
    public PaymentType paymentType;
    
    public Payment() {
        this.id = new Date().getTime();
        this.paymentStatus = PaymentStatus.CRIADO;
    }
    
    public Payment(BigDecimal vlAmount, PaymentType paymentType) {
        this.id = new Date().getTime();
        this.paymentStatus = PaymentStatus.CRIADO;
        this.vlAmount = vlAmount;
        this.paymentType = paymentType;
    }
    

}

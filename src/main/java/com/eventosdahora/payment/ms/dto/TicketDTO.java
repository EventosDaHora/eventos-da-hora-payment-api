package com.eventosdahora.payment.ms.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class TicketDTO {

    private Long id;

    private Long ammount;
}

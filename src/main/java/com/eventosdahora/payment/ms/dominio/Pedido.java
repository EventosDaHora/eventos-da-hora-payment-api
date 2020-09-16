package com.eventosdahora.payment.ms.dominio;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Pedido {

    public static final String IDENTIFICADOR = "ID_PEDIDO";

    private Long id;

    private PedidoState state;

    private PedidoEvent event;
}

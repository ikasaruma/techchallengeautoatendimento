package br.com.fiap.autoatendimento.adapter.primay.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CadastrarPedidoResDto {

    @JsonProperty("id_pedido")
    private String idPedido;

}
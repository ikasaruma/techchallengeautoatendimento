package br.com.fiap.autoatendimento.application.usecase.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CadastrarPedidoOutputDto {
    private Integer idPedido;
}

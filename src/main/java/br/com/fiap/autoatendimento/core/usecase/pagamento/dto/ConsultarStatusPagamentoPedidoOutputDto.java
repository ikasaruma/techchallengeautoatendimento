package br.com.fiap.autoatendimento.core.usecase.pagamento.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsultarStatusPagamentoPedidoOutputDto {
    
    private Integer idPagamento;
    private Integer idPedido;
    private String statusPagamento;

}

package br.com.fiap.autoatendimento.adapter.primay.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CadastrarPedidoReqDto {

    @Positive
    @JsonProperty("id_pedido")
    private Integer idPedido;

    @JsonProperty("cpf")
    private String cpf;

    @NotNull
    @NotEmpty
    @JsonProperty("produtos")
    private List<Integer> produtos;

}

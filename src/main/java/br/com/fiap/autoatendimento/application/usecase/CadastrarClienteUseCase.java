package br.com.fiap.autoatendimento.application.usecase;

import br.com.fiap.autoatendimento.application.port.in.CadastrarClientePortIn;
import br.com.fiap.autoatendimento.application.port.out.ClientePortOut;
import br.com.fiap.autoatendimento.application.usecase.dto.CadastrarClienteInputDto;
import br.com.fiap.autoatendimento.domain.model.cliente.Cliente;
import br.com.fiap.autoatendimento.domain.model.cliente.Cpf;
import br.com.fiap.autoatendimento.domain.model.cliente.Email;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class CadastrarClienteUseCase implements CadastrarClientePortIn {

    private final ClientePortOut clientePortOut;

    @Override
    public void executar(CadastrarClienteInputDto input) {

        final Cliente cliente = Cliente.builder()
                .cpf(new Cpf(input.getCpf()))
                .nome(input.getNome())
                .email(new Email(input.getEmail()))
                .build();

        clientePortOut.salvar(cliente);
    }

}

package br.com.fiap.autoatendimento.adapter.secondary.persistence;

import br.com.fiap.autoatendimento.adapter.secondary.persistence.entity.ClienteEntity;
import br.com.fiap.autoatendimento.adapter.secondary.persistence.repository.ClienteRepository;
import br.com.fiap.autoatendimento.application.port.out.ClientePortOut;
import br.com.fiap.autoatendimento.domain.model.Cliente;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Named
@RequiredArgsConstructor
public class ClienteDao implements ClientePortOut {

    private final ClienteRepository clienteRepository;

    @Override
    public void salvar(Cliente cliente) {

        final ClienteEntity entity = ClienteEntity.builder()
                .cpf(cliente.getCpf())
                .nome(cliente.getNome())
                .email(cliente.getEmail())
                .build();

        clienteRepository.save(entity);
    }

    @Override
    public Optional<Cliente> buscarPorCpf(String cpf) {
        return clienteRepository.findById(cpf)
                .map(entity -> Cliente.builder()
                        .cpf(entity.getCpf())
                        .nome(entity.getNome())
                        .email(entity.getEmail())
                        .build());
    }

}

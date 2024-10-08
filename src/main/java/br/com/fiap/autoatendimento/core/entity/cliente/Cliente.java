package br.com.fiap.autoatendimento.core.entity.cliente;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

	private Cpf cpf;
	private String nome;
	private Email email;

	public String getCpf() {

		return cpf.getValue();
	}

	public String getEmail() {

		return email.getValue();
	}

}

package br.com.fiap.autoatendimento.application.usecase.exception;

public class ClienteNaoEncontradoException extends RuntimeException {

    public ClienteNaoEncontradoException(String msg){
        super(msg);
    }

}

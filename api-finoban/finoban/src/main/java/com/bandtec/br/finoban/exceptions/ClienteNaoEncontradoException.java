package com.bandtec.br.finoban.exceptions;

public class ClienteNaoEncontradoException extends ExceptionGeneric{

    public ClienteNaoEncontradoException() {
        super("FIN02", "Cliente não encontrado.");
    }
}

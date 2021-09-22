package com.bandtec.br.finoban.exceptions;

public class EmailNaoEncontradoException extends ExceptionGeneric {

    public EmailNaoEncontradoException() {
        super("FIN07", "Email não encontrado.");
    }
}

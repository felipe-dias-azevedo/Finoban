package com.bandtec.br.finoban.dominio.exceptions;

import com.bandtec.br.finoban.dominio.exceptions.configuracao.ExceptionGeneric;

public class EmailNaoEncontradoException extends ExceptionGeneric {

    public EmailNaoEncontradoException() {
        super("FIN07", "Email não encontrado.");
    }
}

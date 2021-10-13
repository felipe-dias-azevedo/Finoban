package com.bandtec.br.finoban.dominio.exceptions;

import com.bandtec.br.finoban.dominio.exceptions.configuracao.ExceptionGeneric;

public class ClienteNaoEncontradoException extends ExceptionGeneric {

    public ClienteNaoEncontradoException() {
        super("FIN002", "Cliente não encontrado.");
    }
}

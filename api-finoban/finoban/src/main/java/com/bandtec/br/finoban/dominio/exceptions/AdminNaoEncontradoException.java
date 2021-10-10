package com.bandtec.br.finoban.dominio.exceptions;

import com.bandtec.br.finoban.dominio.exceptions.configuracao.ExceptionGeneric;

public class AdminNaoEncontradoException extends ExceptionGeneric {
    public AdminNaoEncontradoException() {
        super("FIN016", "Admin não encontrado.");
    }
}

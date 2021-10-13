package com.bandtec.br.finoban.dominio.exceptions;

import com.bandtec.br.finoban.dominio.exceptions.configuracao.ExceptionGeneric;

public class AdminNaoLogadoException extends ExceptionGeneric {
    public AdminNaoLogadoException() {
        super("FIN020", "Não encontra-se admin logado.");
    }
}

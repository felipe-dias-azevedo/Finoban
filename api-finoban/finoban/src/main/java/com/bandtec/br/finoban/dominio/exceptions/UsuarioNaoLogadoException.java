package com.bandtec.br.finoban.dominio.exceptions;

import com.bandtec.br.finoban.dominio.exceptions.configuracao.ExceptionGeneric;

public class UsuarioNaoLogadoException extends ExceptionGeneric {
    public UsuarioNaoLogadoException() {
        super("FIN010", "Usuário não logado");
    }
}

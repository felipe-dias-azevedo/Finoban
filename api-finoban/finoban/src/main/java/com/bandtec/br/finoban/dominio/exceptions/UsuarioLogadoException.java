package com.bandtec.br.finoban.dominio.exceptions;

import com.bandtec.br.finoban.dominio.exceptions.configuracao.ExceptionGeneric;

public class UsuarioLogadoException extends ExceptionGeneric {
    public UsuarioLogadoException() {
        super("FIN009", "Usuario já logado.");
    }
}

package com.bandtec.br.finoban.dominio.exceptions;

import com.bandtec.br.finoban.dominio.exceptions.configuracao.ExceptionGeneric;

public class UsuarioJaCadastradoException extends ExceptionGeneric {
    public UsuarioJaCadastradoException() {
        super("FI0N11", "Usuário já cadastrado");
    }
}

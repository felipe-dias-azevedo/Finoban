package com.bandtec.br.finoban.exceptions;

public class UsuarioJaCadastradoException extends ExceptionGeneric{
    public UsuarioJaCadastradoException() {
        super("FIN11", "Usuário já cadastrado");
    }
}

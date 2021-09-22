package com.bandtec.br.finoban.exceptions;

public class UsuarioNaoLogadoException extends ExceptionGeneric{
    public UsuarioNaoLogadoException() {
        super("FIN10", "Usuário não logado");
    }
}

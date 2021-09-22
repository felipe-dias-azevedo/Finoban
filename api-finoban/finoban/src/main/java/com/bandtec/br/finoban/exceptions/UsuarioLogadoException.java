package com.bandtec.br.finoban.exceptions;

public class UsuarioLogadoException extends ExceptionGeneric{
    public UsuarioLogadoException() {
        super("FIN09", "Usuario já logado.");
    }
}

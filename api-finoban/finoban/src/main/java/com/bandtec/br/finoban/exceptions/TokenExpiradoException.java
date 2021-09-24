package com.bandtec.br.finoban.exceptions;

public class TokenExpiradoException extends ExceptionGeneric{
    public TokenExpiradoException() {
        super("FIN13", "Token Expirado.");
    }
}

package com.bandtec.br.finoban.dominio.exceptions;

import com.bandtec.br.finoban.dominio.exceptions.configuracao.ExceptionGeneric;

public class TokenExpiradoException extends ExceptionGeneric {
    public TokenExpiradoException() {
        super("FIN13", "Token Expirado.");
    }
}

package com.bandtec.br.finoban.dominio.exceptions;

import com.bandtec.br.finoban.dominio.exceptions.configuracao.ExceptionGeneric;

public class TokenInvalidoException extends ExceptionGeneric {
    public TokenInvalidoException() {
        super("FIN014", "Token Inválido");
    }
}

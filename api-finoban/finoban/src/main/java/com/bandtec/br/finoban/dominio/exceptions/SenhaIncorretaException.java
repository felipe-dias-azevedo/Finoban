package com.bandtec.br.finoban.dominio.exceptions;

import com.bandtec.br.finoban.dominio.exceptions.configuracao.ExceptionGeneric;

public class SenhaIncorretaException extends ExceptionGeneric {
    public SenhaIncorretaException() {
        super("FIN008", "Senha incorreta.");
    }
}

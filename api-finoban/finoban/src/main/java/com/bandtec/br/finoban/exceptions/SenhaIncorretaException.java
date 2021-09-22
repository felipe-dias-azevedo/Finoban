package com.bandtec.br.finoban.exceptions;

public class SenhaIncorretaException extends ExceptionGeneric{
    public SenhaIncorretaException() {
        super("FIN08", "Senha incorreta.");
    }
}

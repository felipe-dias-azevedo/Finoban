package com.bandtec.br.finoban.exceptions;

public class EnviarEmailException extends ExceptionGeneric{
    public EnviarEmailException() {
        super("FIN12", "Erro ao enviar Email");
    }
}

package com.bandtec.br.finoban.dominio.exceptions;

import com.bandtec.br.finoban.dominio.exceptions.configuracao.ExceptionGeneric;

public class EnviarEmailException extends ExceptionGeneric {
    public EnviarEmailException() {
        super("FIN12", "Erro ao enviar Email");
    }
}

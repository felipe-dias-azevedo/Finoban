package com.bandtec.br.finoban.dominio.exceptions;

import com.bandtec.br.finoban.dominio.exceptions.configuracao.ExceptionGeneric;

public class FalhaAoEnviarEmailException extends ExceptionGeneric {
    public FalhaAoEnviarEmailException() {
        super("FIN012", "Erro ao enviar Email");
    }
}

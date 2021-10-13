package com.bandtec.br.finoban.dominio.exceptions;

import com.bandtec.br.finoban.dominio.exceptions.configuracao.ExceptionGeneric;

public class AdminLogadoException extends ExceptionGeneric {
    public AdminLogadoException() {
        super("FIN019", "Admin já se encontra logado.");
    }
}

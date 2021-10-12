package com.bandtec.br.finoban.dominio.exceptions;

import com.bandtec.br.finoban.dominio.exceptions.configuracao.ExceptionGeneric;

public class AdminJaCadastradoException extends ExceptionGeneric {
    public AdminJaCadastradoException() {
        super("FIN018", "Admin já cadastrado.");
    }
}

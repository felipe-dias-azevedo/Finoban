package com.bandtec.br.finoban.dominio.exceptions;

import com.bandtec.br.finoban.dominio.exceptions.configuracao.ExceptionGeneric;

public class ErroRemoverDadoRelacionadoException extends ExceptionGeneric {
    public ErroRemoverDadoRelacionadoException() {
        super("FIN021", "Erro ao remover dado relacionado em outra tabela.");
    }
}

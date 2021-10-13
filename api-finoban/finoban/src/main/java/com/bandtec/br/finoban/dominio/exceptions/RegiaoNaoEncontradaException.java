package com.bandtec.br.finoban.dominio.exceptions;

import com.bandtec.br.finoban.dominio.exceptions.configuracao.ExceptionGeneric;

public class RegiaoNaoEncontradaException extends ExceptionGeneric {
    public RegiaoNaoEncontradaException() {
        super("FIN003", "Região não encontrada.");
    }
}

package com.bandtec.br.finoban.dominio.exceptions;

import com.bandtec.br.finoban.dominio.exceptions.configuracao.ExceptionGeneric;

public class AvaliacaoNaoEncontradaException extends ExceptionGeneric {
    public AvaliacaoNaoEncontradaException() {
        super("FIN004", "Avaliação não encontrada");
    }
}

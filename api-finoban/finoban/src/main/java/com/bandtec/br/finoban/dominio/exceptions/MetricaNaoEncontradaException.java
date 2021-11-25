package com.bandtec.br.finoban.dominio.exceptions;

import com.bandtec.br.finoban.dominio.exceptions.configuracao.ExceptionGeneric;

public class MetricaNaoEncontradaException extends ExceptionGeneric {

    public MetricaNaoEncontradaException() {
        super("FIN022", "Métrica não encontrada.");
    }
}

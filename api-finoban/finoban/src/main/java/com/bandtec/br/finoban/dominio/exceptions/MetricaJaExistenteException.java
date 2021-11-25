package com.bandtec.br.finoban.dominio.exceptions;

import com.bandtec.br.finoban.dominio.exceptions.configuracao.ExceptionGeneric;

public class MetricaJaExistenteException extends ExceptionGeneric {
    public MetricaJaExistenteException() {
        super("FIN023", "Métrica já existente.");
    }
}

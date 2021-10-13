package com.bandtec.br.finoban.dominio.exceptions;

import com.bandtec.br.finoban.dominio.exceptions.configuracao.ExceptionGeneric;

public class AvalPositivoIncorretoException extends ExceptionGeneric {
    public AvalPositivoIncorretoException() {
        super("FIN005", "AvalPositivoEnum Incorreto.");
    }
}

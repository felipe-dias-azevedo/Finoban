package com.bandtec.br.finoban.dominio.exceptions;

import com.bandtec.br.finoban.dominio.exceptions.configuracao.ExceptionGeneric;

public class FeedbackNullException extends ExceptionGeneric {
    public FeedbackNullException() {
        super("FIN006", "feedbackAval não pode ser null.");
    }
}

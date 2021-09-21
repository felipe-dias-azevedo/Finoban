package com.bandtec.br.finoban.exceptions;

public class FeedbackNullException extends ExceptionGeneric{
    public FeedbackNullException() {
        super("FIN05", "feedbackAval não pode ser null.");
    }
}

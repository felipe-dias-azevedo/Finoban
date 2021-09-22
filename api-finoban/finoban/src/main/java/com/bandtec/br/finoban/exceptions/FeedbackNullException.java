package com.bandtec.br.finoban.exceptions;

public class FeedbackNullException extends ExceptionGeneric{
    public FeedbackNullException() {
        super("FIN06", "feedbackAval não pode ser null.");
    }
}

package com.bandtec.br.finoban.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AcessoNaoEncontradoException extends ExceptionGeneric{
    public AcessoNaoEncontradoException() {
        super("FIN01", "Acesso não encontrado.");
    }
}

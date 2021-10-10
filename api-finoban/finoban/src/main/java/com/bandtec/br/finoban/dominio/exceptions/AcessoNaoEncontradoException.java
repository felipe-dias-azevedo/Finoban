package com.bandtec.br.finoban.dominio.exceptions;

import com.bandtec.br.finoban.dominio.exceptions.configuracao.ExceptionGeneric;

public class AcessoNaoEncontradoException extends ExceptionGeneric {
    public AcessoNaoEncontradoException() {
        super("FIN001", "Acesso não encontrado.");
    }
}

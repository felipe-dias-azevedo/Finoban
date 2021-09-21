package com.bandtec.br.finoban.exceptions;

public class RegiaoNaoEncontradaException extends ExceptionGeneric{
    public RegiaoNaoEncontradaException() {
        super("FIN03", "Região não encontrada.");
    }
}

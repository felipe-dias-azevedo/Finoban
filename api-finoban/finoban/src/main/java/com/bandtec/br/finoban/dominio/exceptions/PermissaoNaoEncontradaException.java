package com.bandtec.br.finoban.dominio.exceptions;

import com.bandtec.br.finoban.dominio.exceptions.configuracao.ExceptionGeneric;

public class PermissaoNaoEncontradaException extends ExceptionGeneric {
public PermissaoNaoEncontradaException() {
    super("FIN015", "Permissão não encontrada.");
}
}

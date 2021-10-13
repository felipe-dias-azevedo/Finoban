package com.bandtec.br.finoban.dominio.exceptions;

import com.bandtec.br.finoban.dominio.exceptions.configuracao.ExceptionGeneric;

public class CargoInexistenteException extends ExceptionGeneric {
    public CargoInexistenteException() {
        super("FIN017", "Cargo não existente");
    }
}

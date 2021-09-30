package com.bandtec.br.finoban.dominio.exceptions.configuracao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiError {

    private String code;
    private String message;

    public ApiError(String code, String message){
        super();
        this.code = code;
        this.message = message;
    }
}

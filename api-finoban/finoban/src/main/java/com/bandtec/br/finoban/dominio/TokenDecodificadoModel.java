package com.bandtec.br.finoban.dominio;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TokenDecodificadoModel {

    private String email;
    private String token;
    private Date date;

    public TokenDecodificadoModel(String email, String token, Date date) {
        this.email = email;
        this.token = token;
        this.date = date;
    }

    public TokenDecodificadoModel() {
    }
}

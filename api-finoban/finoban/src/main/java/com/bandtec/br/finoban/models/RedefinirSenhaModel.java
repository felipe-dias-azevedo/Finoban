package com.bandtec.br.finoban.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RedefinirSenhaModel {

    private String email;
    private String novaSenha;
    private String tokenJwt;
}

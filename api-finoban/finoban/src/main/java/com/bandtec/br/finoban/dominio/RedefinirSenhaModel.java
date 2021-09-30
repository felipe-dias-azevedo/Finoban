package com.bandtec.br.finoban.dominio;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RedefinirSenhaModel {

    private String email;
    private String novaSenha;
    private String tokenJwt;
}

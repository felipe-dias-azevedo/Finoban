package com.bandtec.br.finoban.dominio.enums;

import lombok.Getter;
import lombok.Setter;

public enum TestStatusGeralEnum {

    PASSOU(0, "Passou"),
    FALHOU(1, "Falhou");

    TestStatusGeralEnum(Integer indice, String avaliacao) {
        this.indice = indice;
        this.avaliacao = avaliacao;
    }

    @Getter
    @Setter
    private Integer indice;

    @Getter
    @Setter
    private String avaliacao;


}

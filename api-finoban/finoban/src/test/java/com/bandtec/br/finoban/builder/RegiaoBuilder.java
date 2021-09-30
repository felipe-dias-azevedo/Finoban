package com.bandtec.br.finoban.builder;

import com.bandtec.br.finoban.dominio.entidades.Regiao;

import java.time.LocalDateTime;

public class RegiaoBuilder {
    private Regiao regiao;

    public RegiaoBuilder() {
        this.regiao = new Regiao();
    }

    public RegiaoBuilder criarRegiao() {
        this.regiao.setValorRegiao(400000);
        this.regiao.setDescricaoRegiao("Interlagos");
        this.regiao.setDataCraw(LocalDateTime.now());
        this.regiao.setIdRegiao(1);
        return this;
    }

    public Regiao getRegiao() {
        return regiao;
    }
}

package com.bandtec.br.finoban.builder;

import com.bandtec.br.finoban.dominio.requisicao.BancoRequisicao;

public class RestServiceBuilder {
    private BancoRequisicao bancoRequisicao;

    public RestServiceBuilder() {
        this.bancoRequisicao = new BancoRequisicao();
    }

    public RestServiceBuilder criarRequisicao() {
        this.bancoRequisicao.setCnpj("123");
        this.bancoRequisicao.setRenda(40000);
        this.bancoRequisicao.setTempoFinanciamento(25);
        this.bancoRequisicao.setValorImovel(1_200_000);
        return this;
    }

    public BancoRequisicao getBancoRequisicao() {
        return bancoRequisicao;
    }
}

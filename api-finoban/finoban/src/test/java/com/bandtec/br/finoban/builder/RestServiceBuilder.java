package com.bandtec.br.finoban.builder;

import com.bandtec.br.finoban.dominio.requisicao.BancoRequisicaoModel;

public class RestServiceBuilder {
    private BancoRequisicaoModel bancoRequisicao;

    public RestServiceBuilder() {
        this.bancoRequisicao = new BancoRequisicaoModel();
    }

    public RestServiceBuilder criarRequisicao() {
        this.bancoRequisicao.setCpf("123");
        this.bancoRequisicao.setRenda(40000);
        this.bancoRequisicao.setTempoFinanciamento(25);
        this.bancoRequisicao.setValorImovel(1_200_000);
        return this;
    }

    public BancoRequisicaoModel getBancoRequisicao() {
        return bancoRequisicao;
    }
}

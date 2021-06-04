package com.bandtec.br.finoban.builder;

import com.bandtec.br.finoban.entidades.Acesso;
import com.bandtec.br.finoban.entidades.Avaliacao;

import java.time.LocalDateTime;

public class AvaliacaoBuilder {
    private Avaliacao avaliacao;

    public AvaliacaoBuilder() {
        this.avaliacao = new Avaliacao();
    }

    public AvaliacaoBuilder criarAvaliacao() {
        this.avaliacao.setIdAvaliacao(1);
        this.avaliacao.setDataAval(LocalDateTime.now());
        AcessoBuilder acessoBuilder = new AcessoBuilder();
        this.avaliacao.setFkAcesso(acessoBuilder.criarAcesso().setBancoCifra().setPaginaSaidaHome().getAcesso());
        return this;
    }

    public AvaliacaoBuilder setAvaliacaoGostou() {
        this.avaliacao.setAvalPositivo("Gostou");
        this.avaliacao.setFeedbackAval("Muito boa solução");
        return this;
    }

    public AvaliacaoBuilder setAvaliacaoNaoGostou() {
        this.avaliacao.setAvalPositivo("Não gostou");
        this.avaliacao.setFeedbackAval("Não gostei, prefiro ir presencial");
        return this;
    }

    public AvaliacaoBuilder setAvaliacaoNaoDeuFeedback() {
        this.avaliacao.setAvalPositivo("Não deu feedback");
        return this;
    }

    public AvaliacaoBuilder setAvaliacaoSemFeedback() {
        this.avaliacao.setFeedbackAval(null);
        return this;
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }
}

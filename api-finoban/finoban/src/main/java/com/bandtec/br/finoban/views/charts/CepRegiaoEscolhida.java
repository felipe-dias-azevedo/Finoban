package com.bandtec.br.finoban.views.charts;

public class CepRegiaoEscolhida {

    private String cep;
    private String nomeRegiao;

    public CepRegiaoEscolhida(String cep, String nomeRegiao) {
        this.cep = cep;
        this.nomeRegiao = nomeRegiao;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNomeRegiao() {
        return nomeRegiao;
    }

    public void setNomeRegiao(String nomeRegiao) {
        this.nomeRegiao = nomeRegiao;
    }
}

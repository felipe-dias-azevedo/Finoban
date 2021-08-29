package com.bandtec.br.finoban.views.charts;

public class BairrosRegioes {

    private String bairro;
    private String regiao;

    public BairrosRegioes(String bairro, String regiao) {
        this.bairro = bairro;
        this.regiao = regiao;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }
}

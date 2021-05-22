package com.bandtec.br.finoban.views.charts;

public class RegiaoRenda {

    private String nomeRegiao;
    private Double valorRegiao;
    private Double renda;

    public RegiaoRenda(String nomeRegiao, Double valorRegiao, Double renda) {
        this.nomeRegiao = nomeRegiao;
        this.valorRegiao = valorRegiao;
        this.renda = renda;
    }

    public String getNomeRegiao() {
        return nomeRegiao;
    }

    public void setNomeRegiao(String nomeRegiao) {
        this.nomeRegiao = nomeRegiao;
    }

    public Double getValorRegiao() {
        return valorRegiao;
    }

    public void setValorRegiao(Double valorRegiao) {
        this.valorRegiao = valorRegiao;
    }

    public Double getRenda() {
        return renda;
    }

    public void setRenda(Double renda) {
        this.renda = renda;
    }
}

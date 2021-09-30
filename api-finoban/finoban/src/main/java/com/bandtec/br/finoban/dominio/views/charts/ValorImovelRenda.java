package com.bandtec.br.finoban.dominio.views.charts;

public class ValorImovelRenda {

    private Double valorImovel;
    private Double renda;

    public ValorImovelRenda(Double valorImovel, Double renda) {
        this.valorImovel = valorImovel;
        this.renda = renda;
    }

    public Double getValorImovel() {
        return valorImovel;
    }

    public void setValorImovel(Double valorImovel) {
        this.valorImovel = valorImovel;
    }

    public Double getRenda() {
        return renda;
    }

    public void setRenda(Double renda) {
        this.renda = renda;
    }
}

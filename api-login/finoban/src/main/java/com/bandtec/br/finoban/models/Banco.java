package com.bandtec.br.finoban.models;

public class Banco {

    public String nome;
    public Double valorImovel;
    public Double taxaJuros;
    public Double dfi;
    public Double mip;
    public Double taxaAdministracao;
    public Double valorCet;


    public String getNome() {
        return nome;
    }

    public Double getValorCet() {
        return valorCet;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }



    public void setValorImovel(Double valorImovel) {
        this.valorImovel = valorImovel;
    }



    public void setTaxaJuros(Double taxaJuros) {
        this.taxaJuros = taxaJuros;
    }



    public void setDfi(Double dfi) {
        this.dfi = dfi;
    }


    public void setMip(Double mip) {
        this.mip = mip;
    }



    public void setTaxaAdministracao(Double taxaAdministracao) {
        this.taxaAdministracao = taxaAdministracao;
    }


    public void setValorCet(Double valorCet) {
        this.valorCet = valorCet;
    }
}

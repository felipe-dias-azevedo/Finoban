package br.com.finoban.demo;

public class Empresas implements Taxavel{

    private String nome;
    private Double valorImovel;
    private Double dfi;
    private Double taxaJuros;
    private Double mip;
    private Double taxaAdministracao;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }



    public void setValorImovel(Double valorImovel) {
        this.valorImovel = valorImovel;
    }



    public void setDfi(Double dfi) {
        this.dfi = dfi;
    }



    public void setTaxaJuros(Double taxaJuros) {
        this.taxaJuros = taxaJuros;
    }



    public void setMip(Double mip) {
        this.mip = mip;
    }


    @Override
    public double getValorCet() {
        return (this.taxaJuros * this.valorImovel) + this.dfi + (this.mip * this.valorImovel)
                + this.taxaAdministracao;
    }



    public void setTaxaAdministracao(Double taxaAdministracao) {
        this.taxaAdministracao = taxaAdministracao;

    }

}

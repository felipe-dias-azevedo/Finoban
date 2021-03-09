package br.com.bandtec.ApiOpenBanking;

public class TaxavelResponse {

    private String nome;
    private Double valorImovel;
    private Double dfi;
    private Double taxaJuros;
    private Double mip;
    private Double taxaAdministracao;
    private Double valorCet;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValorImovel() {
        return valorImovel;
    }

    public void setValorImovel(Double valorImovel) {
        this.valorImovel = valorImovel;
    }

    public Double getDfi() {
        return dfi;
    }

    public void setDfi(Double dfi) {
        this.dfi = dfi;
    }

    public Double getTaxaJuros() {
        return taxaJuros;
    }

    public void setTaxaJuros(Double taxaJuros) {
        this.taxaJuros = taxaJuros;
    }

    public Double getMip() {
        return mip;
    }

    public void setMip(Double mip) {
        this.mip = mip;
    }

    public Double getValorCet() {
        return valorCet;
    }

    public void setValorCet(Double valorCet) {
        this.valorCet = valorCet;
    }

    public Double getTaxaAdministracao() {
        return taxaAdministracao;
    }

    public void setTaxaAdministracao(Double taxaAdministracao) {
        this.taxaAdministracao = taxaAdministracao;
    }
}

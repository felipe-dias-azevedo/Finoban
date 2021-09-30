package com.bandtec.br.finoban.dominio;

public class Data {

    private Double taxa;
    private Double taxaAdministracao;
    private Double dfi;
    private Double mip;
    private Double taxaTotal;

    public Double getTaxa() {
        return taxa;
    }

    public void setTaxa(Double taxa) {
        this.taxa = taxa;
    }

    public Double getTaxaAdministracao() {
        return taxaAdministracao;
    }

    public void setTaxaAdministracao(Double taxaAdministracao) {
        this.taxaAdministracao = taxaAdministracao;
    }

    public Double getDfi() {
        return dfi;
    }

    public void setDfi(Double dfi) {
        this.dfi = dfi;
    }

    public Double getMip() {
        return mip;
    }

    public void setMip(Double mip) {
        this.mip = mip;
    }

    public Double getTaxaTotal() {
        return taxaTotal;
    }

    public void setTaxaTotal(Double taxaTotal) {
        this.taxaTotal = taxaTotal;
    }
}

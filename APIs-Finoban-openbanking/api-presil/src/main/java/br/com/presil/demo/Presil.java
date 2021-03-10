package br.com.presil.demo;

public class Presil {

    private String nome;
    private Double valorImovel;
    private Double taxaJuros;
    private Double dfi;
    private Double mip;
    private Double taxaAdministracao;

    public Presil (String nome, Double taxaJuros, Double mip,
                       Double taxaAdministracao, Double valorImovel) {
        this.nome = nome;
        this.valorImovel = valorImovel;
        if(this.valorImovel < 400_000.0){
            this.dfi = 0.005 * this.valorImovel;
        }if (this.valorImovel < 1_000_000.0){
            this.dfi = 0.006 * this.valorImovel;
        }else{
            this.dfi = 0.008 * this.valorImovel;
        }
        this.taxaJuros = taxaJuros;
        this.mip = mip;
        this.taxaAdministracao = taxaAdministracao;

    }

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

    public Double getTaxaJuros() {
        return taxaJuros;
    }

    public void setTaxaJuros(Double taxaJuros) {
        this.taxaJuros = taxaJuros;
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

    public Double getTaxaAdministracao() {
        return taxaAdministracao;
    }

    public void setTaxaAdministracao(Double taxaAdministracao) {
        this.taxaAdministracao = taxaAdministracao;
    }

}

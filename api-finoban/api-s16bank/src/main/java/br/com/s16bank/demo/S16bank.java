package br.com.s16bank.demo;

public class S16bank {

    private String nome;
    private Double taxaJuros;
    private Double valorImovel;
    private Double dfi;
    private Double mip;
    private Double taxaAdministracao;



    public S16bank(String nome, Double taxaJuros, Double mip,
                   Double taxaAdministracao, Double valorImovel) {
        this.nome = nome;
        this.valorImovel = valorImovel;
        if(this.valorImovel < 300_000.0){
            this.dfi = 0.004 * this.valorImovel;
        }if (this.valorImovel < 700_000.0){
            this.dfi = 0.007 * this.valorImovel;
        }else{
            this.dfi = 0.009 * this.valorImovel;
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

    public Double getTaxaJuros() {
        return taxaJuros;
    }

    public void setTaxaJuros(Double taxaJuros) {
        this.taxaJuros = taxaJuros;
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

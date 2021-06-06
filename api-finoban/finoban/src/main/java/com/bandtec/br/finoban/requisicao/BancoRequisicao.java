package com.bandtec.br.finoban.requisicao;

public class BancoRequisicao {

    private String cnpj;
    private Integer renda;
    private Integer valorImovel;
    private Integer tempoFinanciamento;

    public BancoRequisicao(String cnpj, Integer renda, Integer valorImovel, Integer tempoFinanciamento) {
        this.cnpj = cnpj;
        this.renda = renda;
        this.valorImovel = valorImovel;
        this.tempoFinanciamento = tempoFinanciamento;
    }

    public BancoRequisicao() {

    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Integer getRenda() {
        return renda;
    }

    public void setRenda(Integer renda) {
        this.renda = renda;
    }

    public Integer getValorImovel() {
        return valorImovel;
    }

    public void setValorImovel(Integer valorImovel) {
        this.valorImovel = valorImovel;
    }

    public Integer getTempoFinanciamento() {
        return tempoFinanciamento;
    }

    public void setTempoFinanciamento(Integer tempoFinanciamento) {
        this.tempoFinanciamento = tempoFinanciamento;
    }
}

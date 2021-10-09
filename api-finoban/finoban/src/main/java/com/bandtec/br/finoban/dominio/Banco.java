package com.bandtec.br.finoban.dominio;

public class Banco {

    private Integer cpf;
    private Integer renda;
    private Integer valorImovel;
    private Integer tempoFinanciamento;

    public Banco(){
    }

    public Integer getCpf() {
        return cpf;
    }

    public void setCpf(Integer cpf) {
        this.cpf = cpf;
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

package com.bandtec.br.finoban.dominio.requisicao;

import org.springframework.beans.factory.annotation.Required;

public class BancoRequisicao {

    private String cpf;
    private Integer renda;
    private Integer valorImovel;
    private Integer tempoFinanciamento;

    public BancoRequisicao(String cpf, Integer renda, Integer valorImovel, Integer tempoFinanciamento) {
        this.cpf = cpf;
        this.renda = renda;
        this.valorImovel = valorImovel;
        this.tempoFinanciamento = tempoFinanciamento;
    }

    public BancoRequisicao() {

    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
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

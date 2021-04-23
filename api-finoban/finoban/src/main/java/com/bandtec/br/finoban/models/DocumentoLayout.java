package com.bandtec.br.finoban.models;

public class DocumentoLayout {

    private String nomeBanco;
    private String nomeCliente;
    private Integer valorFinanciamento;
    private String regiao;
    private Integer tempoFinanciamento;

    public DocumentoLayout(String nomeBanco, String nomeCliente, Integer valorFinanciamento, String regiao, Integer tempoFinanciamento) {
        this.nomeBanco = nomeBanco;
        this.nomeCliente = nomeCliente;
        this.valorFinanciamento = valorFinanciamento;
        this.regiao = regiao;
        this.tempoFinanciamento = tempoFinanciamento;
    }

    public String getNomeBanco() {
        return nomeBanco;
    }

    public void setNomeBanco(String nomeBanco) {
        this.nomeBanco = nomeBanco;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public Integer getValorFinanciamento() {
        return valorFinanciamento;
    }

    public void setValorFinanciamento(Integer valorFinanciamento) {
        this.valorFinanciamento = valorFinanciamento;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public Integer getTempoFinanciamento() {
        return tempoFinanciamento;
    }

    public void setTempoFinanciamento(Integer tempoFinanciamento) {
        this.tempoFinanciamento = tempoFinanciamento;
    }
}

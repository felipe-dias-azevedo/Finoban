package com.bandtec.br.finoban.dominio.views.charts;

public class CepRegiaoEscolhida {

    private String bairroCliente;
    private String regiaoEscolhida;
    private Integer contagem;

    public CepRegiaoEscolhida(String bairroCliente, String regiaoEscolhida, Integer contagem) {
        this.bairroCliente = bairroCliente;
        this.regiaoEscolhida = regiaoEscolhida;
        this.contagem = contagem;
    }

    public String getBairroCliente() {
        return bairroCliente;
    }

    public void setBairroCliente(String bairroCliente) {
        this.bairroCliente = bairroCliente;
    }

    public String getRegiaoEscolhida() {
        return regiaoEscolhida;
    }

    public void setRegiaoEscolhida(String regiaoEscolhida) {
        this.regiaoEscolhida = regiaoEscolhida;
    }

    public Integer getContagem() {
        return contagem;
    }

    public void setContagem(Integer contagem) {
        this.contagem = contagem;
    }
}

package com.bandtec.br.finoban.dominio.views.charts;

public class PorcentualPerdas {

    private Double porcentual;

    public PorcentualPerdas(Double confirmado, Double naoConfirmado) {
        this.porcentual = arredondar(100 * naoConfirmado / (confirmado + naoConfirmado));
    }

    public Double arredondar(double valor) {
        valor = Math.round(valor * 100);
        return valor / 100;
    }

    public Double getPorcentual() {
        return porcentual;
    }

    public void setPorcentual(Double porcentual) {
        this.porcentual = porcentual;
    }
}

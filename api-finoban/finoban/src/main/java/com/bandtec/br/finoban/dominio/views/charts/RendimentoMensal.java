package com.bandtec.br.finoban.dominio.views.charts;

import java.time.LocalDate;

public class RendimentoMensal {

    public static final Integer TEMPO_LIMITE = 30;

    private LocalDate data;
    private Double valor;

    public RendimentoMensal(LocalDate data, Double valor) {
        this.data = data;
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    //    private List<LocalDate> datas;
//    private List<Double> valores;
//
//    public RendimentoMensal() {
//        this.datas = new ArrayList<LocalDate>();
//        this.valores = new ArrayList<Double>();
//    }
//
//    public void adicionarValor(LocalDate dia, Double valor) {
//        datas.add(dia);
//        valores.add(valor);
//    }
//
//
//    public List<LocalDate> getDatas() {
//        return datas;
//    }
//
//    public void setDatas(List<LocalDate> datas) {
//        this.datas = datas;
//    }
//
//    public List<Double> getValores() {
//        return valores;
//    }
//
//    public void setValores(List<Double> valores) {
//        this.valores = valores;
//    }
}

package com.bandtec.br.finoban.views.charts;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RendimentoMensal {

    public static final Integer TEMPO_LIMITE = 30;
    private List<LocalDate> datas;
    private List<Double> valores;

    public RendimentoMensal() {
        this.datas = new ArrayList<LocalDate>();
        this.valores = new ArrayList<Double>();
    }

    public void adicionarValor(LocalDate dia, Double valor) {
        datas.add(dia);
        valores.add(valor);
    }


    public List<LocalDate> getDatas() {
        return datas;
    }

    public void setDatas(List<LocalDate> datas) {
        this.datas = datas;
    }

    public List<Double> getValores() {
        return valores;
    }

    public void setValores(List<Double> valores) {
        this.valores = valores;
    }
}

package com.bandtec.br.finoban.views.charts;

import java.util.List;

public class ProjecaoRendimento {

    private double a;
    private double b;

    public void gerarRegressaoLinear(List<Double> x, List<Double> y) {

        double totalX = 0.0;
        double totalY = 0.0;
        double totalX2 = 0.0;
        double totalY2 = 0.0;
        double totalXy = 0.0;

        for (int i = 0; i < RendimentoMensal.TEMPO_LIMITE; i++) {
            totalX += x.get(i);
            totalY += y.get(i);
            totalX2 += Math.pow(x.get(i), 2);
            totalY2 += Math.pow(y.get(i), 2);
            totalXy += x.get(i) * y.get(i);
        }

        double segundaParteConta = (RendimentoMensal.TEMPO_LIMITE * totalX2) - (Math.pow(totalX, 2));
        a = (
                (totalY * totalX2) - (totalX * totalXy))
                /
                segundaParteConta;
        b = (
                (RendimentoMensal.TEMPO_LIMITE * totalXy) - (totalX * totalY))
                /
                segundaParteConta;
    }
    
    public double calculoRegressaoLinear(Double valorCalculo) {
        return (valorCalculo * b) + a;
    }
}

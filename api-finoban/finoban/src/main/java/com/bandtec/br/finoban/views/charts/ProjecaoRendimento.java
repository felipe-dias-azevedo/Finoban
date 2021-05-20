package com.bandtec.br.finoban.views.charts;

import java.util.List;

public class ProjecaoRendimento extends RendimentoMensal {

    private double a;
    private double b;

    public void gerarRegressaoLinear(List<Double> x, List<Double> y) {

        double b;
        double a;

        double totalX = 0.0;
        double totalY = 0.0;
        double totalX2 = 0.0;
        double totalY2 = 0.0;
        double totalXy = 0.0;

        for (int i = 0; i < TEMPO_LIMITE; i++) {
            totalX += x.get(i);
            totalY += y.get(i);
            totalX2 += Math.pow(x.get(i), 2);
            totalY2 += Math.pow(y.get(i), 2);
            totalXy += x.get(i) * y.get(i);
        }

        a = (((totalY * totalX2) - (totalX * totalXy)) / ((TEMPO_LIMITE * totalX2) - (Math.pow(totalX, 2))));
        b = (((TEMPO_LIMITE * totalXy) - (totalX * totalY)) / ((TEMPO_LIMITE * totalX2) - (Math.pow(totalX, 2))));
//        print("Linha de melhor ajuste: ");
//        print("y = "+ str(b) +"x + "+ str(a));
//        Integer valor_para_calcular = int(input("Insira um valor para calcular: "));
//        print("y =", valor_para_calcular * b + a);
    }
    
    public double calculoRegressaoLinear(Double valorCalculo) {
        return (valorCalculo * b) + a;
    }
}

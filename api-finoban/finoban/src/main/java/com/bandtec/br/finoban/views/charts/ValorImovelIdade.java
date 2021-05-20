package com.bandtec.br.finoban.views.charts;

import java.time.LocalDate;
import java.time.Period;

public class ValorImovelIdade {

    public static Integer converterDataParaIdade(LocalDate dataNascimento) {
        Period idade = Period.between(dataNascimento, LocalDate.now());
        return idade.getYears();
    }
}

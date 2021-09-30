package com.bandtec.br.finoban.dominio.views.charts;

import java.time.LocalDate;
import java.time.Period;

public class ValorImovelIdade {

    private Integer idade;
    private Double valorImovel;

    public ValorImovelIdade(Integer idade, Double valorImovel) {
        this.idade = idade;
        this.valorImovel = valorImovel;
    }

    public static Integer converterDataParaIdade(LocalDate dataNascimento) {
        Period idade = Period.between(dataNascimento, LocalDate.now());
        return idade.getYears();
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Double getValorImovel() {
        return valorImovel;
    }

    public void setValorImovel(Double valorImovel) {
        this.valorImovel = valorImovel;
    }
}

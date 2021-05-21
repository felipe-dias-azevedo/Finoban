package com.bandtec.br.finoban.views.charts;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class TempoPermanencia {

    public static final LocalTime LIMITE_TEMPO = LocalTime.of(0, 5, 0);
    public static final Integer INTERVALO_TEMPO = 15;
    List<Integer> quantidade;
    List<LocalTime> permanencia;

    public TempoPermanencia() {
        this.quantidade = new ArrayList<Integer>();
        this.permanencia = new ArrayList<LocalTime>();
    }

    public LocalTime diferencaTempo(LocalDateTime entrada, LocalDateTime saida) {
        LocalDateTime periodo = LocalDateTime.from(entrada);
        Long segundos = periodo.until(saida, ChronoUnit.SECONDS);
        return periodo.plusSeconds(segundos).toLocalTime();
    }

    public void adicionarValor(LocalTime tempo, Integer quantidade) {
        this.permanencia.add(tempo);
        this.quantidade.add(quantidade);
    }

    public List<Integer> getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(List<Integer> quantidade) {
        this.quantidade = quantidade;
    }

    public List<LocalTime> getPermanencia() {
        return permanencia;
    }

    public void setPermanencia(List<LocalTime> permanencia) {
        this.permanencia = permanencia;
    }
}

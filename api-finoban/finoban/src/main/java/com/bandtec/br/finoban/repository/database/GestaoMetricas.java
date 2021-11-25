package com.bandtec.br.finoban.repository.database;

import com.bandtec.br.finoban.dominio.MetricaModel;
import com.bandtec.br.finoban.dominio.entidades.Metricas;

import java.util.List;

public interface GestaoMetricas {

    MetricaModel obterMetrica(int id);
    List<MetricaModel> obterTodasMetricas();
    void postarMetrica(MetricaModel metrica);
    void deletarMetrica(int id);

}

package com.bandtec.br.finoban.service.core;

import com.bandtec.br.finoban.dominio.MetricaModel;
import com.bandtec.br.finoban.dominio.entidades.Metricas;
import com.bandtec.br.finoban.dominio.exceptions.MetricaNaoEncontradaException;
import com.bandtec.br.finoban.repository.database.GestaoMetricas;
import com.bandtec.br.finoban.repository.database.MetricasRepository;
import com.bandtec.br.finoban.repository.database.UsuarioRepository;
import com.bandtec.br.finoban.service.core.financiamento.FinanciamentoApiCifraImpl;
import com.bandtec.br.finoban.service.usuarios.GestaoUsuariosService;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class MetricasService implements GestaoMetricas {

    private GestaoUsuariosService usuariosService;
    private MetricasRepository metricasRepository;
    private Gson gson;
    private static final Logger log = LogManager.getLogger(MetricasService.class.getName());

    @Override
    public MetricaModel obterMetrica(int id) {
        var metrica = metricasRepository.findById(id);
        log.info("OBTENDO MÉTRICA: id -" + id);
        if (metrica.isEmpty()) {
            log.error("Métrica não encontrada");
            throw new MetricaNaoEncontradaException();
        }


        log.info("Métrica: - " + this.gson.toJson(metrica.get().toModel()));
        return metrica.get().toModel();
    }

    @Override
    public List<MetricaModel> obterTodasMetricas() {
        return Metricas.toList(metricasRepository.findAll());
    }

    @Override
    public void postarMetrica(MetricaModel metrica) {
        var usuario = this.usuariosService.resgatarUsuarioPeloId(metrica.getUsuario().getId());
        var metricas = new Metricas(usuario, metrica.getNomeBanco(), metrica.getValorImovel());
        log.info("POSTANDO MÉTRICA - " + this.gson.toJson(metricas));
        metricasRepository.save(metricas);
    }

    @Override
    public void deletarMetrica(int id) {
        var metrica = this.metricasRepository.findById(id);
        log.info("DELETANDO MÉTRICA: id - " + id);
        if (metrica.isEmpty()) {
            log.error("Métrica não encontrada");
            throw new MetricaNaoEncontradaException();
        }

        log.info("MÉTRICA DELETADA - " + this.gson.toJson(metrica));
        this.metricasRepository.deleteById(id);
    }
}

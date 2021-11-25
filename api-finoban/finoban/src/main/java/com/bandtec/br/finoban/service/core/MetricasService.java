package com.bandtec.br.finoban.service.core;

import com.bandtec.br.finoban.dominio.MetricaModel;
import com.bandtec.br.finoban.dominio.entidades.Metricas;
import com.bandtec.br.finoban.dominio.exceptions.MetricaNaoEncontradaException;
import com.bandtec.br.finoban.repository.database.GestaoMetricas;
import com.bandtec.br.finoban.repository.database.MetricasRepository;
import com.bandtec.br.finoban.repository.database.UsuarioRepository;
import com.bandtec.br.finoban.service.usuarios.GestaoUsuariosService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class MetricasService implements GestaoMetricas {

    private GestaoUsuariosService usuariosService;
    private MetricasRepository metricasRepository;

    @Override
    public MetricaModel obterMetrica(int id) {
        var metrica = metricasRepository.findById(id);
        if (metrica.isEmpty())
            throw new MetricaNaoEncontradaException();

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
        metricasRepository.save(metricas);
    }

    @Override
    public void deletarMetrica(int id) {
        var metrica = this.metricasRepository.findById(id);
        if (metrica.isEmpty())
            throw new MetricaNaoEncontradaException();

        this.metricasRepository.deleteById(id);
    }
}

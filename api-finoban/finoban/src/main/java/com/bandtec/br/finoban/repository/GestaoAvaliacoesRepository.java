package com.bandtec.br.finoban.repository;

import com.bandtec.br.finoban.dominio.entidades.Avaliacao;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GestaoAvaliacoesRepository {
    List<Avaliacao> listarAvaliacoes();
    Avaliacao resgatarAvaliacaoPeloId(int id);
    void deletarAvaliacaoPeloId(int id);
    void cadastrarAvaliacao(Avaliacao avaliacao);
}

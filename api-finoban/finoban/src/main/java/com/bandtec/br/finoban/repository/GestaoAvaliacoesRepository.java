package com.bandtec.br.finoban.repository;

import com.bandtec.br.finoban.entidades.Avaliacao;
import org.springframework.http.ResponseEntity;

public interface GestaoAvaliacoesRepository {
    ResponseEntity listarAvaliacoes();
    ResponseEntity resgatarAvaliacaoPeloId(int id);
    ResponseEntity deletarAvaliacaoPeloId(int id);
    ResponseEntity cadastrarAvaliacao(Avaliacao avaliacao);
}

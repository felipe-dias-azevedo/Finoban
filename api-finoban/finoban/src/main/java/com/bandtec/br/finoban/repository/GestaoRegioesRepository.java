package com.bandtec.br.finoban.repository;

import com.bandtec.br.finoban.entidades.Regiao;
import org.springframework.http.ResponseEntity;

public interface GestaoRegioesRepository {
    ResponseEntity listarRegioes();
    ResponseEntity resgatarRegiaoPeloId(int id);
    ResponseEntity registrarRegiao(Regiao regiao);
    ResponseEntity deletarRegiaoPeloId(int id);
    ResponseEntity atualizarRegiao(Regiao regiao);

}

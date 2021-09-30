package com.bandtec.br.finoban.repository;

import com.bandtec.br.finoban.dominio.entidades.Regiao;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GestaoRegioesRepository {
    List<Regiao> listarRegioes();
    Regiao resgatarRegiaoPeloId(int id);
    void registrarRegiao(Regiao regiao);
    void deletarRegiaoPeloId(int id);
    Regiao atualizarRegiao(Regiao regiao);

}

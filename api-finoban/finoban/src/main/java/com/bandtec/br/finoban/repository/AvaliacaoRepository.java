package com.bandtec.br.finoban.repository;

import com.bandtec.br.finoban.entidades.Acesso;
import com.bandtec.br.finoban.entidades.Avaliacao;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AvaliacaoRepository extends CrudRepository <Avaliacao, Integer> {
    List<Avaliacao> findByFkAcessoEquals(Acesso fkAcesso);
}

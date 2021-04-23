package com.bandtec.br.finoban.demo;

import com.bandtec.br.finoban.models.Avaliacao;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AvaliacaoRepository extends CrudRepository <Avaliacao, Integer> {

    List<Avaliacao> findByCadastro(Cadastro cadastro, Sort sort);

}

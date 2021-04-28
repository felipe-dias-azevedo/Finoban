package com.bandtec.br.finoban.repository;

import com.bandtec.br.finoban.entidades.Cadastro;
import org.springframework.data.repository.CrudRepository;

public interface CadastroRepository extends CrudRepository <Cadastro, Integer> {

    Cadastro findByEmailContaining(String email);

}

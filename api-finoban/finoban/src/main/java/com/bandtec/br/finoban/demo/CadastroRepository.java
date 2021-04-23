package com.bandtec.br.finoban.demo;

import org.springframework.data.repository.CrudRepository;

public interface CadastroRepository extends CrudRepository <Cadastro, Integer> {

    Cadastro findByEmailContaining(String email);

}

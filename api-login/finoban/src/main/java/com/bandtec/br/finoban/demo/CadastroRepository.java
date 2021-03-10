package com.bandtec.br.finoban.demo;

import org.springframework.data.repository.CrudRepository;

import com.bandtec.br.finoban.demo.Cadastro;

public interface CadastroRepository extends CrudRepository <Cadastro, Integer> {

    Cadastro findByEmailContaining(String email);

}

package com.bandtec.br.finoban;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bandtec.br.finoban.Cadastro;

import java.util.Optional;

public interface CadastroRepository extends CrudRepository <Cadastro, Integer> {

    Cadastro findByEmailContaining(String email);

}

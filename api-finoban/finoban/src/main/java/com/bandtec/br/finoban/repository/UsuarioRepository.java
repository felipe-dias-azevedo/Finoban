package com.bandtec.br.finoban.repository;

import com.bandtec.br.finoban.entidades.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface CadastroRepository extends CrudRepository <Usuario, Integer> {

    Usuario findByEmailContaining(String email);

}

package com.bandtec.br.finoban.repository;

import com.bandtec.br.finoban.entidades.Usuario;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UsuarioRepository extends CrudRepository <Usuario, Integer> {

    Usuario findByEmailContaining(String email);
    boolean existsUsuarioByEmail(String email);

    @Modifying
    @Transactional
    @Query("update Usuario u set u.senha = :senha where u.id = :idUsuario")
    void redefinirSenhaUsuario(String senha, int idUsuario);
}

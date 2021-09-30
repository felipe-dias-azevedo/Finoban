package com.bandtec.br.finoban.repository;

import com.bandtec.br.finoban.dominio.entidades.RedefinicaoSenha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface RedefinicaoSenhaRepository extends JpaRepository<RedefinicaoSenha, Integer> {
    RedefinicaoSenha findAllByTokenJWT(String jwt);

    @Modifying
    @Transactional
    @Query("update RedefinicaoSenha r set r.expirado = true where r.id = :idExpirado")
    void updateCampoExpirado(int idExpirado);

}

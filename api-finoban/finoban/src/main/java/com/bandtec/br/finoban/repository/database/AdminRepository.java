package com.bandtec.br.finoban.repository.database;

import com.bandtec.br.finoban.dominio.entidades.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin findAllByEmail(String email);
    boolean existsByEmail(String email);

    @Modifying
    @Transactional
    @Query("update Admin admin set admin.senha = :senha where admin.idAmin = :idAdmin")
    void redefinirSenhaUsuario(String senha, int idAdmin);

    @Modifying
    @Transactional
    @Query("update Admin admin set admin.permissoes.status = :status where admin.idAmin = :idAdmin")
    void atualizarStatus(boolean status, int idAdmin);
}

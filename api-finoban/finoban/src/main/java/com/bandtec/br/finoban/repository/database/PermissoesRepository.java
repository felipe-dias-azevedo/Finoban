package com.bandtec.br.finoban.repository.database;

import com.bandtec.br.finoban.dominio.entidades.Permissoes;
import com.bandtec.br.finoban.dominio.enums.CargoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface PermissoesRepository extends JpaRepository<Permissoes, Integer> {

    @Modifying
    @Transactional
    @Query("update Permissoes permissao set permissao.cargo = :cargo where permissao.idPermissao = :idPermissao")
    void updateCargo(CargoEnum cargo, int idPermissao);
}

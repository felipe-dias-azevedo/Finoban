package com.bandtec.br.finoban.repository;

import com.bandtec.br.finoban.dominio.entidades.Acesso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AcessoRepository extends JpaRepository<Acesso, Integer> {
    List<Acesso> findTop5ByOrderByDataHoraSaidaDesc();

    @Query(value = "select * FROM acesso order by id_entrada asc limit 50",
          nativeQuery = true)
    List<Acesso>  findAllByIdEntrada();
}

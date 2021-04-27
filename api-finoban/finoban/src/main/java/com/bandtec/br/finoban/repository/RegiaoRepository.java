package com.bandtec.br.finoban.repository;

import com.bandtec.br.finoban.entidades.Regiao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface RegiaoRepository extends JpaRepository<Regiao, Integer> {
    List<Regiao> findAllByOrderByIdRegiao();

    @Modifying
    @Transactional
    @Query("update Regiao r set r.descricaoRegiao = ?1, r.valorRegiao = ?2 , r.dataCraw = ?3 where r.idRegiao = ?4")
    void setRegiaoById(String descricaoRegiao, Integer valorRegiao, LocalDate dataCraw, Integer idRegiao);
}

package com.bandtec.br.finoban.repository;

import com.bandtec.br.finoban.dominio.entidades.Regiao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface RegiaoRepository extends JpaRepository<Regiao, Integer> {

    @Query(
        value = "SELECT * FROM regiao r where data_craw = (SELECT data_craw FROM regiao r2 order by data_craw desc LIMIT 1)",
        nativeQuery = true
    )
    List<Regiao> findAllRegiaoLatest();

    @Modifying
    @Transactional
    @Query("update Regiao r set r.descricaoRegiao = ?1, r.valorRegiao = ?2 , r.dataCraw = ?3 where r.idRegiao = ?4")
    void setRegiaoById(String descricaoRegiao, Integer valorRegiao, LocalDateTime dataCraw, Integer idRegiao);
}

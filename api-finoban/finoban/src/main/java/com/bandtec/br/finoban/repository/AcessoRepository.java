package com.bandtec.br.finoban.repository;

import com.bandtec.br.finoban.entidades.Acesso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AcessoRepository extends JpaRepository<Acesso, Integer> {
    List<Acesso> findTop5ByOrderByDataHoraSaidaDesc();
}

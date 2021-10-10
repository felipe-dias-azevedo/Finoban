package com.bandtec.br.finoban.repository;

import com.bandtec.br.finoban.dominio.entidades.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
}

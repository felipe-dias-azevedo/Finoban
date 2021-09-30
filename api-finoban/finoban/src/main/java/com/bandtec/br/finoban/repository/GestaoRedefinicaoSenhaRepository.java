package com.bandtec.br.finoban.repository;

import com.bandtec.br.finoban.dominio.entidades.RedefinicaoSenha;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GestaoRedefinicaoSenhaRepository {
    List<RedefinicaoSenha> listarRedefinicoes();
}

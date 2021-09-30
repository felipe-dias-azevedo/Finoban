package com.bandtec.br.finoban.repository;

import com.bandtec.br.finoban.dominio.entidades.Acesso;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GestaoAcessosRepository {
    Acesso resgatarAcessoPeloId(int id);
    List<Acesso> resgatarTodosAcessos();
    void deletarAcessoPeloId(int id);
    void postAcesso(Acesso acesso);
}

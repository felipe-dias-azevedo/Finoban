package com.bandtec.br.finoban.repository;

import com.bandtec.br.finoban.entidades.Acesso;
import org.springframework.http.ResponseEntity;

public interface GestaoAcessosRepository {
    ResponseEntity resgatarAcessoPeloId(int id);
    ResponseEntity resgatarTodosAcessos();
    ResponseEntity deletarAcessoPeloId(int id);
    ResponseEntity postAcesso(Acesso acesso);
}

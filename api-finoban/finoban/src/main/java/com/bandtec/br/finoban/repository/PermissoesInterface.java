package com.bandtec.br.finoban.repository;

import com.bandtec.br.finoban.dominio.entidades.Permissoes;

import java.util.List;

public interface PermissoesInterface {
    void cadastrarPermissao(Permissoes permissoes);
    List<Permissoes> resgatarTodasAsPermissoes();

    void atualizarPermissao(Permissoes permissoes);
}

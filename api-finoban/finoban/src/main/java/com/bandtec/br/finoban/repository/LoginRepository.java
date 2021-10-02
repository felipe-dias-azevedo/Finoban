package com.bandtec.br.finoban.repository;

import com.bandtec.br.finoban.dominio.entidades.Usuario;

public interface LoginRepository {
    boolean verificaUsuarioLogado(Usuario usuario);
    Usuario logarUsuario(Usuario usuario);
    void realizarLogoffUsuario(Usuario usuario);
}

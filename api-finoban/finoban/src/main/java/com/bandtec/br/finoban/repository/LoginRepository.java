package com.bandtec.br.finoban.repository;

import com.bandtec.br.finoban.dominio.entidades.Usuario;
import com.bandtec.br.finoban.dominio.resposta.RespostaLogin;

import java.util.List;

public interface LoginRepository {
    boolean verificaUsuarioLogado(Usuario usuario);
    RespostaLogin logarUsuario(Usuario usuario);
    void realizarLogoffUsuario(Usuario usuario);
}

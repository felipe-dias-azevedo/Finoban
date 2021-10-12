package com.bandtec.br.finoban.repository;

import com.bandtec.br.finoban.dominio.entidades.Usuario;
import com.bandtec.br.finoban.dominio.resposta.respostasLogin.RespostaLoginUsuario;

public interface LoginRepository {
    boolean verificaUsuarioLogado(Usuario usuario);
    RespostaLoginUsuario logarUsuario(Usuario usuario);
    void realizarLogoffUsuario(Usuario usuario);
}

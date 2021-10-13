package com.bandtec.br.finoban.repository;

import com.bandtec.br.finoban.dominio.entidades.Admin;
import com.bandtec.br.finoban.dominio.resposta.respostasLogin.RespostaLogin;
import com.bandtec.br.finoban.dominio.resposta.respostasLogin.RespostaLoginUsuario;

public interface LoginAdminRepository {

    boolean verificaUsuarioLogado(Admin usuario);
    RespostaLogin logarUsuario(Admin usuario);
    void realizarLogoffUsuario(Admin usuario);

}

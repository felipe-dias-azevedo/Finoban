package com.bandtec.br.finoban.dominio.resposta.respostasLogin;

import com.bandtec.br.finoban.dominio.entidades.Admin;
import com.bandtec.br.finoban.dominio.entidades.Permissoes;
import com.bandtec.br.finoban.dominio.resposta.respostasLogin.RespostaLoginUsuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RespostaLoginAdmin extends RespostaLoginUsuario {

    private Permissoes permissoes;

    public RespostaLoginAdmin(Admin admin, String token) {
        this.setNome(admin.getNome());
        this.setEmail(admin.getEmail());
        this.setSenha(admin.getSenha());
        this.permissoes = admin.getPermissoes();
        this.setToken(token);
    }
}

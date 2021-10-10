package com.bandtec.br.finoban.dominio.entidades;

import com.bandtec.br.finoban.dominio.resposta.UsuarioRespostaSimples;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAmin;

    @OneToOne
    private Permissoes permissoes;

    @OneToOne
    private Usuario usuario;

    public int getIdAmin() {
        return idAmin;
    }

    public void setIdAmin(int idAmin) {
        this.idAmin = idAmin;
    }

    public Permissoes getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(Permissoes permissoes) {
        this.permissoes = permissoes;
    }

    public UsuarioRespostaSimples getUsuario() {
        return new UsuarioRespostaSimples(usuario);
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

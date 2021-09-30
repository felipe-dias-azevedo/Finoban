package com.bandtec.br.finoban.dominio.entidades;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class RedefinicaoSenha {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne
    private Usuario usuario;

    private String tokenJWT;

    private boolean expirado;

    public RedefinicaoSenha(Usuario usuario, String tokenJWT) {
        this.usuario = usuario;
        this.tokenJWT = tokenJWT;
    }

    public RedefinicaoSenha() {
    }
}

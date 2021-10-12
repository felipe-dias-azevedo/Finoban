package com.bandtec.br.finoban.dominio.entidades;

import com.bandtec.br.finoban.dominio.resposta.UsuarioRespostaSimples;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAmin;

    private String nome;
    private String email;
    private String senha;

    @OneToOne
    private Permissoes permissoes;

}

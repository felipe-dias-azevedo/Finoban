package com.bandtec.br.finoban.builder;

import com.bandtec.br.finoban.dominio.entidades.Usuario;

import java.time.LocalDateTime;

public class CadastroBuilder {
    private Usuario usuario;

    public CadastroBuilder() {
        this.usuario = new Usuario();
    }

    public CadastroBuilder criarCadastro() {
        usuario.setId(1);
        usuario.setNumero(597);
        usuario.setDataCriacao(LocalDateTime.now());
        usuario.setCep("01414001");
        usuario.setCpf("01234567890000");
        usuario.setSenha("teste123");
        usuario.setEmail("teste.teste@teste.com");
        usuario.setBairro("Consolação");
        return this;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}

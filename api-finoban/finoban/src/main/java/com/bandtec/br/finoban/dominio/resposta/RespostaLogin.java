package com.bandtec.br.finoban.dominio.resposta;

import com.bandtec.br.finoban.dominio.entidades.Usuario;

public class RespostaLogin extends Usuario {
    private String token;

    public RespostaLogin(Usuario usuario, String token) {
        super(usuario.getCnpj(), usuario.getNome(), usuario.getEmail(), usuario.getSenha(), usuario.getCep(), usuario.getNumero());
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}

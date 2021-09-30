package com.bandtec.br.finoban.builder;

import com.bandtec.br.finoban.dominio.Login;

public class LoginBuilder {
    private Login login;

    public LoginBuilder() {
        this.login = new Login();
    }

    public LoginBuilder criarLogin() {
        this.login.setEmail("teste.teste@teste.com");
        this.login.setSenha("teste123");
        return this;
    }

    public Login getLogin() {
        return login;
    }
}

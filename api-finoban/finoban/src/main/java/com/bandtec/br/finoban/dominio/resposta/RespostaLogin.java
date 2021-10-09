package com.bandtec.br.finoban.dominio.resposta;

import com.bandtec.br.finoban.dominio.entidades.Usuario;

import java.text.ParseException;

public class RespostaLogin {

    private String cpf;
    private String nome;
    private String email;
    private String senha;
    private String token;

    public RespostaLogin(Usuario usuario, String token) {
        this.setCpf(usuario.getCpf());
        this.setNome(usuario.getNome());
        this.setEmail(usuario.getEmail());
        this.setSenha(usuario.getSenha());
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCpf() throws ParseException {
        if (cpf == null)
            return cpf;

        cpf = cpf.replace("/","")
                .replace(".","")
                .replace("-","");
        char[] array = cpf.toCharArray();
        array[0] = 'X'; array[1] = 'X'; array[2] = 'X'; array[cpf.length()-2] = 'X'; array[cpf.length()-1] = 'X';
        return new String().valueOf(array);
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}

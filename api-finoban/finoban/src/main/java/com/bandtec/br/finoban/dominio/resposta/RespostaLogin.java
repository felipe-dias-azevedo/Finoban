package com.bandtec.br.finoban.dominio.resposta;

import com.bandtec.br.finoban.dominio.entidades.Usuario;

import java.text.ParseException;

public class RespostaLogin {
    private String cnpj;
    private String nome;
    private String email;
    private String senha;
    private String token;

    public RespostaLogin(Usuario usuario, String token) {
        this.setCnpj(usuario.getCnpj());
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

    public String getCnpj() throws ParseException {
        if (cnpj == null)
            return cnpj;

        cnpj = cnpj.replace("/","")
                .replace(".","")
                .replace("-","");
        char[] array = cnpj.toCharArray();
        array[0] = 'X'; array[1] = 'X'; array[2] = 'X'; array[cnpj.length()-2] = 'X'; array[cnpj.length()-1] = 'X';
        return new String().valueOf(array);
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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

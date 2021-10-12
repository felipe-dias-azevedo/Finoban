package com.bandtec.br.finoban.dominio.resposta.respostasLogin;

import com.bandtec.br.finoban.dominio.entidades.Usuario;
import com.bandtec.br.finoban.dominio.resposta.respostasLogin.RespostaLogin;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.text.ParseException;

public class RespostaLoginUsuario extends RespostaLogin {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String cpf;

    public RespostaLoginUsuario(Usuario usuario, String token) {
        this.setCpf(usuario.getCpf());
        this.setNome(usuario.getNome());
        this.setEmail(usuario.getEmail());
        this.setSenha(usuario.getSenha());
        this.setToken(token);
    }

    public RespostaLoginUsuario() { }

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
}

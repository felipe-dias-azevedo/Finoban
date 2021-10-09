package com.bandtec.br.finoban.dominio.resposta;

import com.bandtec.br.finoban.dominio.entidades.Usuario;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class UsuarioRespostaSimples {

    private int idUsuario;
    private String cpf;
    private String nome;
    private String email;
    private String senha;
    private String bairro;

    public UsuarioRespostaSimples(Usuario usuario) {
        this.idUsuario = usuario.getId();
        this.cpf = usuario.getCpf();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
        this.bairro = usuario.getBairro();
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

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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


    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public static List<UsuarioRespostaSimples> converterListaUsuarioParaUsuarioSimples(List<Usuario> usuarios) {
        List<UsuarioRespostaSimples> usuarioRespostaSimplesList = new ArrayList<>();
        for (int i = 0; i < usuarios.size(); i++)
            usuarioRespostaSimplesList.add(new UsuarioRespostaSimples(usuarios.get(i)));


        return usuarioRespostaSimplesList;
    }


}

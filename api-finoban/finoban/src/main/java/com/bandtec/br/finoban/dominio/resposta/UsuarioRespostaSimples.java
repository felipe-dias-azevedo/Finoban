package com.bandtec.br.finoban.dominio.resposta;

import com.bandtec.br.finoban.dominio.entidades.Usuario;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class UsuarioRespostaSimples {

    private int idUsuario;
    private String cnpj;
    private String nome;
    private String email;
    private String senha;
    private String bairro;

    public UsuarioRespostaSimples(Usuario usuario) {
        this.idUsuario = usuario.getId();
        this.cnpj = usuario.getCnpj();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
        this.bairro = usuario.getBairro();
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

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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

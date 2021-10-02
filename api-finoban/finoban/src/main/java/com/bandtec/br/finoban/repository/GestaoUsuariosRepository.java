package com.bandtec.br.finoban.repository;

import com.bandtec.br.finoban.dominio.RedefinicaoSenhaModel;
import com.bandtec.br.finoban.dominio.TokenDecodificadoModel;
import com.bandtec.br.finoban.dominio.entidades.Usuario;
import com.bandtec.br.finoban.dominio.Login;
import com.bandtec.br.finoban.dominio.RedefinirSenhaModel;
import org.springframework.http.ResponseEntity;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public interface GestaoUsuariosRepository {
    void cadastrarUsuario(Usuario usuario);
    List<Usuario> listarUsuarios();
    Usuario resgatarUsuarioPeloId(int id);
    void deletarUsuarioPeloId(int id);
    Usuario atualizarDadosCadastrais(Usuario usuario);
    Usuario atualizarDadosCadastrais(Usuario usuario, RedefinirSenhaModel redefinirSenhaModel);
    Usuario efetuarLogin(Login login);
    void efetuarLogoff(Login login);
    void iniciarRedefinicaoSenha(RedefinicaoSenhaModel redefinicaoSenhaModel) throws MessagingException, IOException;
    TokenDecodificadoModel verificarRedeficicaoSenha(String jwt);
}

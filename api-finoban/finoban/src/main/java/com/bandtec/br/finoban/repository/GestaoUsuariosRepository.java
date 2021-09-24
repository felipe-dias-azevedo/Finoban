package com.bandtec.br.finoban.repository;

import com.bandtec.br.finoban.entidades.Usuario;
import com.bandtec.br.finoban.models.Login;
import com.bandtec.br.finoban.models.RedefinirSenhaModel;
import org.springframework.http.ResponseEntity;

import javax.mail.MessagingException;
import java.io.IOException;

public interface GestaoUsuariosRepository {
    ResponseEntity cadastrarUsuario(Usuario usuario);
    ResponseEntity listarUsuarios();
    ResponseEntity resgatarUsuarioPeloId(int id);
    ResponseEntity deletarUsuarioPeloId(int id);
    ResponseEntity atualizarDadosCadastrais(Usuario usuario);
    ResponseEntity atualizarDadosCadastrais(Usuario usuario, RedefinirSenhaModel redefinirSenhaModel);
    ResponseEntity efetuarLogin(Login login);
    ResponseEntity efetuarLogoff(Login login);
    ResponseEntity iniciarRedefinicaoSenha(Usuario usuario) throws MessagingException, IOException;
    ResponseEntity verificarRedeficicaoSenha(String jwt);
}

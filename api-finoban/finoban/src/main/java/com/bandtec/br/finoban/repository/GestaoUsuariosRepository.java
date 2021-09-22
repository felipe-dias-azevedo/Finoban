package com.bandtec.br.finoban.repository;

import com.bandtec.br.finoban.entidades.Usuario;
import com.bandtec.br.finoban.models.Login;
import org.springframework.http.ResponseEntity;

public interface GestaoUsuariosRepository {
    ResponseEntity cadastrarUsuario(Usuario usuario);
    ResponseEntity listarUsuarios();
    ResponseEntity resgatarUsuarioPeloId(int id);
    ResponseEntity deletarUsuarioPeloId(int id);
    ResponseEntity atualizarDadosCadastrais(Usuario usuario);
    ResponseEntity efetuarLogin(Login login);
    ResponseEntity efetuarLogoff(Login login);
}

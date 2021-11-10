package com.bandtec.br.finoban.controller;

import com.bandtec.br.finoban.builder.UsuarioBuilder;
import com.bandtec.br.finoban.builder.LoginBuilder;
import com.bandtec.br.finoban.dominio.entidades.Usuario;
import com.bandtec.br.finoban.dominio.Login;
import com.bandtec.br.finoban.dominio.exceptions.EmailNaoEncontradoException;
import com.bandtec.br.finoban.dominio.exceptions.SenhaIncorretaException;
import com.bandtec.br.finoban.dominio.exceptions.UsuarioLogadoException;
import com.bandtec.br.finoban.repository.database.UsuarioRepository;
import com.bandtec.br.finoban.dominio.resposta.SingleResponse;
import com.bandtec.br.finoban.service.usuarios.LoginUsuarioService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoginControllerTest {

    @Autowired
    LoginController controller;

    @MockBean
    UsuarioRepository cadastroRepository;

    @MockBean
    LoginUsuarioService loginUsuarioService;

    @Test
    @DisplayName("/POST - Fazer login com usuário já existente - STATUS 200")
    void novoLoginOk() {
        LoginBuilder loginBuilder = new LoginBuilder();
        Login login = loginBuilder.criarLogin().getLogin();
        UsuarioBuilder usuarioBuilder = new UsuarioBuilder();
        Usuario usuario = usuarioBuilder.criarUsuario().getUsuario();
        Mockito.when(cadastroRepository.findByEmailContaining(login.getEmail())).thenReturn(usuario);
        assertThrows(SenhaIncorretaException.class, () -> controller.novoLogin(login));
    }

    @Test
    @DisplayName("/POST - Fazer login com usuário não existente - STATUS 204")
    void novoLoginNotOk() {
        LoginBuilder loginBuilder = new LoginBuilder();
        Login login = loginBuilder.criarLogin().getLogin();
        Mockito.when(cadastroRepository.findByEmailContaining(login.getEmail())).thenReturn(null);
        assertThrows(EmailNaoEncontradoException.class, () -> controller.novoLogin(login));
    }

    @Test
    @DisplayName("/POST - Efetuar logoff de um usuário já logado - STATUS 200")
    void efetuarLogoff() {
        List<String> usuariosLogados = new ArrayList<>();
        LoginBuilder loginBuilder = new LoginBuilder();
        Login login = loginBuilder.criarLogin().getLogin();
        UsuarioBuilder usuarioBuilder = new UsuarioBuilder();
        Usuario usuario = usuarioBuilder.criarUsuario().getUsuario();
        usuariosLogados.add(usuario.getEmail());
        Mockito.when(cadastroRepository.findByEmailContaining(usuario.getEmail())).thenReturn(usuario);
        ResponseEntity<SingleResponse<Usuario>> resposta = controller.efetuarLogoff(login);
        assertEquals(204, resposta.getStatusCodeValue());
    }

    @Test
    @DisplayName("/POST - Efetuar logoff de um usuário que não está logado - STATUS 404")
    void efetutarLogoffNotOk() {
        LoginBuilder loginBuilder = new LoginBuilder();
        Login login = loginBuilder.criarLogin().getLogin();
        Usuario usuario = new UsuarioBuilder().criarUsuario().getUsuario();
        Mockito.when(cadastroRepository.findByEmailContaining(login.getEmail())).thenReturn(usuario);
        Mockito.when(!loginUsuarioService.verificaUsuarioLogado(usuario)).thenReturn(false);
        assertThrows(UsuarioLogadoException.class, () -> controller.efetuarLogoff(login));
    }
}
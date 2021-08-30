package com.bandtec.br.finoban.controller;

import com.bandtec.br.finoban.builder.CadastroBuilder;
import com.bandtec.br.finoban.builder.LoginBuilder;
import com.bandtec.br.finoban.entidades.Usuario;
import com.bandtec.br.finoban.models.Login;
import com.bandtec.br.finoban.repository.CadastroRepository;
import com.bandtec.br.finoban.resposta.ResponseGeneric;
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
    CadastroRepository cadastroRepository;

    @Test
    @DisplayName("/POST - Fazer login com usuário já existente - STATUS 200")
    void novoLoginOk() {
        LoginBuilder loginBuilder = new LoginBuilder();
        Login login = loginBuilder.criarLogin().getLogin();
        CadastroBuilder cadastroBuilder = new CadastroBuilder();
        Usuario usuario = cadastroBuilder.criarCadastro().getUsuario();
        Mockito.when(cadastroRepository.findByEmailContaining(login.getEmail())).thenReturn(usuario);
        ResponseEntity<ResponseGeneric<Usuario>> resposta = controller.novoLogin(login);
        assertEquals(200, resposta.getStatusCodeValue());
        assertEquals(usuario.getSenha(), resposta.getBody().getData().getSenha());
    }

    @Test
    @DisplayName("/POST - Fazer login com usuário não existente - STATUS 204")
    void novoLoginNotOk() {
        LoginBuilder loginBuilder = new LoginBuilder();
        Login login = loginBuilder.criarLogin().getLogin();
        CadastroBuilder cadastroBuilder = new CadastroBuilder();
        Usuario usuario = cadastroBuilder.criarCadastro().getUsuario();
        Mockito.when(cadastroRepository.findByEmailContaining(login.getEmail())).thenReturn(null);
        ResponseEntity<ResponseGeneric> resposta = controller.novoLogin(login);
        assertEquals(204, resposta.getStatusCodeValue());
        assertEquals("Email não encontrado", resposta.getBody().getData());
    }

    @Test
    @DisplayName("/POST - Efetuar logoff de um usuário já logado - STATUS 200")
    void efetuarLogoff() {
        List<String> usuariosLogados = new ArrayList<>();
        LoginBuilder loginBuilder = new LoginBuilder();
        Login login = loginBuilder.criarLogin().getLogin();
        CadastroBuilder cadastroBuilder = new CadastroBuilder();
        Usuario usuario = cadastroBuilder.criarCadastro().getUsuario();
        usuariosLogados.add(usuario.getEmail());
        Mockito.when(cadastroRepository.findByEmailContaining(usuario.getEmail())).thenReturn(usuario);
        ResponseEntity<ResponseGeneric<Usuario>> resposta = controller.efetuarLogoff(login);
        assertEquals(200, resposta.getStatusCodeValue());
        assertEquals(String.format("%s, você foi deslogado com sucesso!", usuario.getNome()), resposta.getBody().
                getData());
    }

    @Test
    @DisplayName("/POST - Efetuar logoff de um usuário que não está logado - STATUS 404")
    void efetutarLogoffNotOk() {
        List<String> usuariosLogados = new ArrayList<>();
        LoginBuilder loginBuilder = new LoginBuilder();
        Login login = loginBuilder.criarLogin().getLogin();
        ResponseEntity resposta = controller.efetuarLogoff(login);
        assertEquals(404, resposta.getStatusCodeValue());
    }
}
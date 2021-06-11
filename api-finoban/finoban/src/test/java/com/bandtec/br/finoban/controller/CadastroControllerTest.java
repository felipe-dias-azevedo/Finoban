package com.bandtec.br.finoban.controller;

import com.bandtec.br.finoban.builder.AvaliacaoBuilder;
import com.bandtec.br.finoban.builder.CadastroBuilder;
import com.bandtec.br.finoban.entidades.Avaliacao;
import com.bandtec.br.finoban.entidades.Usuario;
import com.bandtec.br.finoban.repository.AcessoRepository;
import com.bandtec.br.finoban.repository.AvaliacaoRepository;
import com.bandtec.br.finoban.repository.CadastroRepository;
import com.fasterxml.jackson.databind.util.ArrayIterator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CadastroControllerTest {

    @Autowired
    CadastroController cadastroController;
    @MockBean
    CadastroRepository cadastroRepository;
    @MockBean
    private AvaliacaoRepository avaliacaoRepository;
    @MockBean
    private AcessoRepository acessoRepository;

    @Test
    @DisplayName("/POST - Cadastro de usuário realizado com sucesso - STATUS 201")
    void novoCadastro() {
        CadastroBuilder cadastroBuilder = new CadastroBuilder();
        Usuario usuario = cadastroBuilder.criarCadastro().getUsuario();
        ResponseEntity resposta = cadastroController.novoCadastro(usuario);
        assertEquals(201, resposta.getStatusCodeValue());
    }

    @Test
    @DisplayName("/GET - Lista todos os usuários com sucesso - STATUS 200")
    void listarUsuarios() {
        Iterable<Usuario> usuarioLista = Arrays.asList(new Usuario(), new Usuario(), new Usuario());
        Mockito.when(cadastroRepository.findAll()).thenReturn(usuarioLista);
        ResponseEntity<List<Usuario>> resposta = cadastroController.listarUsuarios();
        assertEquals(200, resposta.getStatusCodeValue());
        assertEquals(3, resposta.getBody().size());
    }

    @Test
    @DisplayName("/GET - Lista todos os usuários, mas não existem nenhum - STATUS 204")
    void listarUsuariosNotOK() {
        Mockito.when(cadastroRepository.findAll()).thenReturn(new ArrayList<>());
        ResponseEntity resposta = cadastroController.listarUsuarios();
        assertEquals(204, resposta.getStatusCodeValue());
    }

    @Test
    @DisplayName("/GET - Resgata um usuário, status 404")
    void getUsuario() {
        CadastroBuilder cadastroBuilder = new CadastroBuilder();
        Usuario usuario = cadastroBuilder.criarCadastro().getUsuario();
        Mockito.when(cadastroRepository.findById(1)).thenReturn(Optional.of(usuario));
        ResponseEntity<Optional<Usuario>> resposta = cadastroController.getUsuario(1);
        assertEquals(200, resposta.getStatusCodeValue());
        assertEquals(usuario.getCnpj(), resposta.getBody().get().getCnpj());
    }

    @Test
    @DisplayName("/GET - Resgata um usuário, porém ele não existe - status 404")
    void getUsuarioNotOK() {
        Mockito.when(cadastroRepository.findById(1)).thenReturn(Optional.empty());
        ResponseEntity<Optional<Usuario>> resposta = cadastroController.getUsuario(1);
        assertEquals(404, resposta.getStatusCodeValue());
    }

    @Test
    @DisplayName("/POST - Criar nova avaliação sendo que usuário gostou da solução - status 201")
    void novaAvaliacaoGostou() {
        AvaliacaoBuilder avaliacaoBuilder = new AvaliacaoBuilder();
        Avaliacao avaliacao = avaliacaoBuilder.criarAvaliacao().setAvaliacaoGostou().getAvaliacao();
        Mockito.when(acessoRepository.existsById(1)).thenReturn(true);
        ResponseEntity resposta = cadastroController.novaAvaliacao(avaliacao);
        assertEquals(201, resposta.getStatusCodeValue());
    }

    @Test
    @DisplayName("/POST - Criar nova avaliação sendo que usuário não gostou da solução - status 201")
    void novaAvaliacaoNaoGostou() {
        AvaliacaoBuilder avaliacaoBuilder = new AvaliacaoBuilder();
        Avaliacao avaliacao = avaliacaoBuilder.criarAvaliacao().setAvaliacaoNaoGostou().getAvaliacao();
        Mockito.when(acessoRepository.existsById(1)).thenReturn(true);
        ResponseEntity resposta = cadastroController.novaAvaliacao(avaliacao);
        assertEquals(201, resposta.getStatusCodeValue());
    }

    @Test
    @DisplayName("/POST - Criar nova avaliação sendo que usuário não gostou da solução, mas não deu feedback" +
            " - status 404")
    void novaAvaliacaoNaoGostouENaoPassouFeedback() {
        AvaliacaoBuilder avaliacaoBuilder = new AvaliacaoBuilder();
        Avaliacao avaliacao = avaliacaoBuilder.criarAvaliacao().setAvaliacaoNaoGostou().setAvaliacaoSemFeedback()
                .getAvaliacao();
        Mockito.when(acessoRepository.existsById(1)).thenReturn(true);
        ResponseEntity resposta = cadastroController.novaAvaliacao(avaliacao);
        assertEquals(400, resposta.getStatusCodeValue());
    }

    @Test
    @DisplayName("/DELETE - Excluir um usuário, status 204")
    void deleteUsuario() {
        Mockito.when(cadastroRepository.existsById(1)).thenReturn(true);
        ResponseEntity resposta = cadastroController.deleteUsuario(1);
        assertEquals(204, resposta.getStatusCodeValue());
    }

    @Test
    @DisplayName("/DELETE - Excluir um usuário, porém ele não existe - STATUS 404")
    void deleteUsuarioNotOk() {
        Mockito.when(cadastroRepository.findById(1)).thenReturn(Optional.empty());
        ResponseEntity resposta = cadastroController.deleteUsuario(1);
        assertEquals(404, resposta.getStatusCodeValue());
    }

    @Test
    @DisplayName("/GET - Listar todos as avaliações, STATUS 200")
    void getAvaliacoesOk() {
        List<Avaliacao> avaliacaos = Arrays.asList(new Avaliacao(), new Avaliacao());
        Mockito.when(avaliacaoRepository.findAll()).thenReturn(avaliacaos);
        ResponseEntity<List<Avaliacao>> resposta = cadastroController.getAvaliacoes();
        assertEquals(200, resposta.getStatusCodeValue());
        assertEquals(2, resposta.getBody().size());
    }

    @Test
    @DisplayName("/GET - Listar todas as avaliações mas não é encontrada nenhuma -" +
            "STATUS 204")
    void getAvaliacoesNotOK() {
        Mockito.when(avaliacaoRepository.findAll()).thenReturn(new ArrayList<>());
        ResponseEntity<List<Avaliacao>> resposta = cadastroController.getAvaliacoes();
        assertEquals(204, resposta.getStatusCodeValue());
        assertEquals(null, resposta.getBody());
    }

    @Test
    @DisplayName("/GET/{id} - Resgatar uma única avaliação - STATUS 200")
    void getAvaliacaoOk() {
        AvaliacaoBuilder avaliacaoBuilder = new AvaliacaoBuilder();
        Avaliacao avaliacao = avaliacaoBuilder.criarAvaliacao().setAvaliacaoNaoGostou().setAvaliacaoSemFeedback()
                .getAvaliacao();
        Mockito.when(avaliacaoRepository.findById(1)).thenReturn(Optional.of(avaliacao));
        ResponseEntity<Optional<Avaliacao>> resposta = cadastroController.getAvaliacao(1);
        assertEquals(200, resposta.getStatusCodeValue());
        assertEquals(avaliacao.getAvalPositivo(), resposta.getBody().get().getAvalPositivo());
    }

    @Test
    @DisplayName("/GET/{id} - Resgatar uma única avaliação, mas ela não existe - STATUS 404")
    void getAvaliacaoNotOK() {
        Mockito.when(avaliacaoRepository.findById(1)).thenReturn(Optional.empty());
        ResponseEntity resposta = cadastroController.getAvaliacao(1);
        assertEquals(404, resposta.getStatusCodeValue());
    }

    @Test
    @DisplayName("/DELETE/{id} - Deleter uma avaliação - STATUS 200")
    void deleteAvaliacaoOk() {
        Mockito.when(avaliacaoRepository.existsById(1)).thenReturn(true);
        ResponseEntity resposta = cadastroController.deleteAvaliacao(1);
        assertEquals(204, resposta.getStatusCodeValue());
    }

    @Test
    @DisplayName("/DELETE/{id} - Deletar uma avaliação, sendo que ela não existe - STATUS 404")
    void deleteAvaliacaoNotOK() {
        Mockito.when(avaliacaoRepository.existsById(1)).thenReturn(false);
        ResponseEntity resposta = cadastroController.deleteAvaliacao(1);
        assertEquals(404, resposta.getStatusCodeValue());
    }
}
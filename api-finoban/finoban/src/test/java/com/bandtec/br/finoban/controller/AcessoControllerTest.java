package com.bandtec.br.finoban.controller;

import com.bandtec.br.finoban.builder.AcessoBuilder;
import com.bandtec.br.finoban.dominio.entidades.Acesso;
import com.bandtec.br.finoban.dominio.exceptions.AcessoNaoEncontradoException;
import com.bandtec.br.finoban.dominio.exceptions.RegiaoNaoEncontradaException;
import com.bandtec.br.finoban.repository.database.AcessoRepository;
import com.bandtec.br.finoban.repository.GestaoAcessosRepository;
import com.bandtec.br.finoban.repository.database.UsuarioRepository;
import com.bandtec.br.finoban.repository.database.RegiaoRepository;
import com.bandtec.br.finoban.dominio.resposta.ResponseGeneric;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class AcessoControllerTest {

    @Autowired
    AcessoController acessoController;
    @MockBean
    UsuarioRepository cadastroRepository;
    @MockBean
    RegiaoRepository regiaoRepository;
    @MockBean
    AcessoRepository acessoRepository;

    @MockBean
    GestaoAcessosRepository gestaoAcessosRepository;


    @Test
    @DisplayName("/POST - Criar acesso - 201")
    void postAcesso() {
        AcessoBuilder acessoBuilder = new AcessoBuilder();
        acessoBuilder.criarAcesso().setBancoCifra().setPaginaSaidaHome().setStatusSaidaConfirmou();
        Mockito.when(cadastroRepository.existsById(1)).thenReturn(true);
        Mockito.when(regiaoRepository.existsById(1)).thenReturn(true);
        ResponseEntity resposta = acessoController.postAcesso(acessoBuilder.getAcesso());
        assertEquals(201, resposta.getStatusCodeValue());
    }

    @Test
    @DisplayName("/POST - Criar acesso, com região inexistente - ")
    void postAcessoNotOK() {
        AcessoBuilder acessoBuilder = new AcessoBuilder();
        acessoBuilder.criarAcesso().setBancoCifra().setPaginaSaidaHome().setStatusSaidaConfirmou();
        Mockito.when(cadastroRepository.existsById(1)).thenReturn(true);
        Mockito.when(regiaoRepository.existsById(1)).thenReturn(false);
        assertThrows(RegiaoNaoEncontradaException.class, () -> acessoController.postAcesso(acessoBuilder.getAcesso()));
    }
    @Test
    @DisplayName("/GET{id} - Requisição de um um único acesso - 200")
    void getAcesso() {
        AcessoBuilder acessoBuilder = new AcessoBuilder();
        acessoBuilder.criarAcesso().setBancoCifra().setPaginaSaidaHome().setStatusSaidaConfirmou();
        Mockito.when(acessoRepository.findById(1)).thenReturn(Optional.of(acessoBuilder.getAcesso()));
        Mockito.when(gestaoAcessosRepository.resgatarAcessoPeloId(1)).thenReturn(acessoBuilder.getAcesso());
        ResponseEntity<ResponseGeneric<Acesso>> resposta = acessoController.getAcesso(1);
        assertEquals(200, resposta.getStatusCodeValue());
//        assertEquals("Teste Legal", resposta.getBody().getData().getFkCliente().getNome());
    }

    @Test
    @DisplayName("/GET{id} - Requisição de um um único acesso e ele não existe - 404")
    void getAcessoNotOK() {
        Mockito.when(acessoRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(AcessoNaoEncontradoException.class, () -> acessoController.getAcesso(1));
    }

    @Test
    @DisplayName("/DELETE - Deletar um acesso existete - 204")
    void deleteAcesso() {
        Mockito.when(acessoRepository.existsById(1)).thenReturn(true);
        ResponseEntity resposta = acessoController.deleteAcesso(1);
        assertEquals(204, resposta.getStatusCodeValue());
    }

    @Test
    @DisplayName("/DELETE - Falha ao deletar um acesso não existente - 404")
    void deleteAcessoNotOK() {
        Mockito.when(!acessoRepository.existsById(2)).thenReturn(true);
        assertThrows(AcessoNaoEncontradoException.class, () -> acessoController.deleteAcesso(2));
    }

    @Test
    @DisplayName("/GET - Resgatar todos os acessos - 200")
    void getAcessos() {
        List<Acesso> acessoList = Arrays.asList(new Acesso(), new Acesso(), new Acesso());
        Mockito.when(acessoRepository.findAll()).thenReturn(acessoList);
        Mockito.when(gestaoAcessosRepository.resgatarTodosAcessos()).thenReturn(acessoList);
        ResponseEntity<ResponseGeneric<List<Acesso>>> resposta = acessoController.getAcessos();
        assertEquals(200, resposta.getStatusCodeValue());
        assertEquals(3, resposta.getBody().getData().size());
    }

    @Test
    @DisplayName("/GET - Resgatar todos os acessos e não existem nenhum - 204")
    void getAcessosNotOK() {
        Mockito.when(acessoRepository.findAll()).thenReturn(new ArrayList<>());
        ResponseEntity resposta = acessoController.getAcessos();
        assertEquals(204, resposta.getStatusCodeValue());
    }

//    @Test
//    @DisplayName("/DELETE - Deletar últimos acesso - 204")
//    void deleteUltimosAcessos() {
//        Acesso acesso = new Acesso();
//        acesso.setIdEntrada(1);
//        Acesso acesso2 = new Acesso();
//        acesso2.setIdEntrada(2);
//        List<Acesso> lista = new ArrayList<>();
//        lista.add(acesso);
//        lista.add(acesso2);
//
//        Mockito.when(acessoRepository.findTop5ByOrderByDataHoraSaidaDesc()).thenReturn(lista);
//        ResponseEntity resposta = acessoController.deleteUltimosAcessos();
//        assertEquals(204, resposta.getStatusCodeValue());
//    }

//    @Test
//    @DisplayName("/DELETE - Deletar últimos acesso e esse não existe- 404")
//    void deleteUltimosAcessosNotOk() {
//        List<Acesso> lista = new ArrayList<>();
//
//        Mockito.when(acessoRepository.findTop5ByOrderByDataHoraSaidaDesc()).thenReturn(lista);
//        ResponseEntity resposta = acessoController.deleteUltimosAcessos();
//        assertEquals(404, resposta.getStatusCodeValue());
//    }
}
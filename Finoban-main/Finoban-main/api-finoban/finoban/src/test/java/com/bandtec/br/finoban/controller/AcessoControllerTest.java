package com.bandtec.br.finoban.controller;

import com.bandtec.br.finoban.builder.AcessoBuilder;
import com.bandtec.br.finoban.entidades.Acesso;
import com.bandtec.br.finoban.repository.AcessoRepository;
import com.bandtec.br.finoban.repository.CadastroRepository;
import com.bandtec.br.finoban.repository.RegiaoRepository;
import com.bandtec.br.finoban.resposta.ResponseGeneric;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.convert.DataSizeUnit;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AcessoControllerTest {

    @Autowired
    AcessoController acessoController;
    @MockBean
    CadastroRepository cadastroRepository;
    @MockBean
    RegiaoRepository regiaoRepository;
    @MockBean
    AcessoRepository acessoRepository;

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
        ResponseEntity<ResponseGeneric> resposta = acessoController.postAcesso(acessoBuilder.getAcesso());
        assertEquals(404, resposta.getStatusCodeValue());
        assertEquals("Não foi encontrado região para este Id", resposta.getBody().getData());
    }
    @Test
    @DisplayName("/GET{id} - Requisição de um um único acesso - 200")
    void getAcesso() {
        Acesso acesso = new Acesso();
        AcessoBuilder acessoBuilder = new AcessoBuilder();
        acessoBuilder.criarAcesso().setBancoCifra().setPaginaSaidaHome().setStatusSaidaConfirmou();
        Mockito.when(acessoRepository.findById(1)).thenReturn(Optional.of(acessoBuilder.getAcesso()));
        ResponseEntity<Optional<Acesso>> resposta = acessoController.getAcesso(1);
        assertEquals(200, resposta.getStatusCodeValue());
        assertEquals("Teste Legal", resposta.getBody().get().getFkCliente().getNome());
    }

    @Test
    @DisplayName("/GET{id} - Requisição de um um único acesso e ele não existe - 404")
    void getAcessoNotOK() {
        Mockito.when(acessoRepository.findById(1)).thenReturn(Optional.empty());
        ResponseEntity<ResponseGeneric> resposta = acessoController.getAcesso(1);
        assertEquals(404, resposta.getStatusCodeValue());
        assertEquals("Não foi encontrado acesso para este Id", resposta.getBody().getData());
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
        Mockito.when(acessoRepository.existsById(2)).thenReturn(false);
        ResponseEntity<ResponseGeneric> resposta = acessoController.deleteAcesso(2);
        assertEquals(404, resposta.getStatusCodeValue());
        assertEquals("Não foi encontrado acesso para este Id", resposta.getBody().getData());
    }

    @Test
    @DisplayName("/GET - Resgatar todos os acessos - 200")
    void getAcessos() {
        List<Acesso> acessoList = Arrays.asList(new Acesso(), new Acesso(), new Acesso());
        Mockito.when(acessoRepository.findAll()).thenReturn(acessoList);
        ResponseEntity<List<Acesso>> resposta = acessoController.getAcessos();
        assertEquals(200, resposta.getStatusCodeValue());
        assertEquals(3, resposta.getBody().size());
    }

    @Test
    @DisplayName("/GET - Resgatar todos os acessos e não existem nenhum - 204")
    void getAcessosNotOK() {
        Mockito.when(acessoRepository.findAll()).thenReturn(new ArrayList<>());
        ResponseEntity resposta = acessoController.getAcessos();
        assertEquals(204, resposta.getStatusCodeValue());
    }
}
package com.bandtec.br.finoban.controller;

import com.bandtec.br.finoban.builder.RegiaoBuilder;
import com.bandtec.br.finoban.entidades.Acesso;
import com.bandtec.br.finoban.entidades.Regiao;
import com.bandtec.br.finoban.repository.RegiaoRepository;
import com.bandtec.br.finoban.resposta.ResponseGeneric;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
class RegiaoControllerTest {

    @Autowired
    RegiaoController regiaoController;

    @MockBean
    RegiaoRepository regiaoRepository;

    @Test
    @DisplayName("/GET - Listar as ultimas regiões cadastradas no banco - STATUS 200")
    void getRegioesOk() {
        List<Regiao> acessoList = Arrays.asList(new Regiao(), new Regiao());
        Mockito.when(regiaoRepository.findAllRegiaoLatest()).thenReturn(acessoList);
        ResponseEntity<List<Regiao>> resposta = regiaoController.getRegioes();
        assertEquals(200, resposta.getStatusCodeValue());
        assertEquals(2, resposta.getBody().size());
    }

    @Test
    @DisplayName("/GET - Listar as últimas regiões cadastradas no banco, sendo que não existe nenhuma -" +
            "STATUS 204")
    void getRegioesNotOk() {
        Mockito.when(regiaoRepository.findAllRegiaoLatest()).thenReturn(new ArrayList<>());
        ResponseEntity resposta = regiaoController.getRegioes();
        assertEquals(204, resposta.getStatusCodeValue());
    }

    @Test
    @DisplayName("/GET/{id} - Resgatar uma única região - STATUS 200")
    void getRegiaoOk() {
        RegiaoBuilder regiaoBuilder = new RegiaoBuilder();
        Regiao regiao = regiaoBuilder.criarRegiao().getRegiao();
        Mockito.when(regiaoRepository.findById(1)).thenReturn(Optional.of(regiao));
        ResponseEntity<Optional<Regiao>> respota = regiaoController.getRegiao(1);
        assertEquals(200, respota.getStatusCodeValue());
        assertEquals(regiao.getValorRegiao(), respota.getBody().get().getValorRegiao());
    }

    @Test
    @DisplayName("/GET/{id} - Resgatar uma única região com id não encontrado - STATUS 404")
    void getRegiaoNotOk() {
        Mockito.when(regiaoRepository.findById(1)).thenReturn(Optional.empty());
        ResponseEntity respota = regiaoController.getRegiao(1);
        assertEquals(404, respota.getStatusCodeValue());
    }

    @Test
    @DisplayName("/POST - Regitrar uma região - STATUS 201")
    void postRegiao() {
        RegiaoBuilder regiaoBuilder = new RegiaoBuilder();
        Regiao regiao = regiaoBuilder.criarRegiao().getRegiao();
        ResponseEntity resposta = regiaoController.postRegiao(regiao);
        assertEquals(201, resposta.getStatusCodeValue());
    }

    @Test
    @DisplayName("/DELETE - Deletar uma região já existente - STATUS 200")
    void deleteRegiaoOk() {
        Mockito.when(regiaoRepository.existsById(1)).thenReturn(true);
        ResponseEntity resposta = regiaoController.deleteRegiao(1);
        assertEquals(200, resposta.getStatusCodeValue());
    }

    @Test
    @DisplayName("/DELETE - Deletar uma região não existente - STATUS 200")
    void deleteRegiaoNotOk() {
        Mockito.when(regiaoRepository.existsById(1)).thenReturn(false);
        ResponseEntity resposta = regiaoController.deleteRegiao(1);
        assertEquals(404, resposta.getStatusCodeValue());
    }

    @Test
    @DisplayName("/PUT - Atualizar uma região já existente - STATUS 200")
    void atualizarRegiaoOk() {
        RegiaoBuilder regiaoBuilder = new RegiaoBuilder();
        Regiao regiao = regiaoBuilder.criarRegiao().getRegiao();
        Mockito.when(regiaoRepository.existsById(regiao.getIdRegiao())).thenReturn(true);
        ResponseEntity<ResponseGeneric> resposta = regiaoController.atualizarRegiao(regiao);
        assertEquals(200, resposta.getStatusCodeValue());
        assertEquals("Região atualizada.", resposta.getBody().getData());
    }

    @Test
    @DisplayName("/PUT - Atualizar uma região não existente - STATUS 404")
    void atualizarRegiaoNotOk() {
        RegiaoBuilder regiaoBuilder = new RegiaoBuilder();
        Regiao regiao = regiaoBuilder.criarRegiao().getRegiao();
        Mockito.when(regiaoRepository.existsById(regiao.getIdRegiao())).thenReturn(false);
        ResponseEntity resposta = regiaoController.atualizarRegiao(regiao);
        assertEquals(404, resposta.getStatusCodeValue());
    }
}
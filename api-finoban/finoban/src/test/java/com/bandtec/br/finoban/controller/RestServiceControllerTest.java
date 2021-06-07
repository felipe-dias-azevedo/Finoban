package com.bandtec.br.finoban.controller;

import com.bandtec.br.finoban.builder.RestServiceBuilder;
import com.bandtec.br.finoban.requisicao.BancoRequisicao;
import com.bandtec.br.finoban.resposta.RespostaApi;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RestServiceControllerTest {

    @Autowired
    RestServiceController controller;


    @Test
    @DisplayName("/POST - Criação de um financiamento com apis externas ligadas - STATUS 200")
    void retornaLista() {
        RestServiceBuilder restServiceBuilder = new RestServiceBuilder();
        BancoRequisicao bancoRequisicao = restServiceBuilder.criarRequisicao().getBancoRequisicao();
        ResponseEntity<List<RespostaApi>> resposta =  controller.realizarFinaciamento(bancoRequisicao);
        assertEquals(200, resposta.getStatusCodeValue());
        assertEquals(3, resposta.getBody().size());
    }

}
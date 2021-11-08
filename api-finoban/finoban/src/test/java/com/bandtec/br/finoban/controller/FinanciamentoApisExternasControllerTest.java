package com.bandtec.br.finoban.controller;

import com.bandtec.br.finoban.builder.RestServiceBuilder;
import com.bandtec.br.finoban.dominio.requisicao.BancoRequisicaoModel;
import com.bandtec.br.finoban.dominio.resposta.SingleResponse;
import com.bandtec.br.finoban.dominio.resposta.RespostaApi;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FinanciamentoApisExternasControllerTest {

    @Autowired
    FinanciamentoApisExternasController controller;


    @Test
    @DisplayName("/POST - Criação de um financiamento com apis externas ligadas - STATUS 200")
    void retornaLista() {
        RestServiceBuilder restServiceBuilder = new RestServiceBuilder();
        BancoRequisicaoModel bancoRequisicao = restServiceBuilder.criarRequisicao().getBancoRequisicao();
        ResponseEntity<SingleResponse<List<RespostaApi>>> resposta =  controller.realizarFinaciamento(bancoRequisicao);
        assertEquals(200, resposta.getStatusCodeValue());
        assertEquals(3, resposta.getBody().getData().size());
    }

}
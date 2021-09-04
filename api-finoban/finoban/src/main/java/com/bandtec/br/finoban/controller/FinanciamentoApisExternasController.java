package com.bandtec.br.finoban.controller;

import com.bandtec.br.finoban.entidades.Regiao;
import com.bandtec.br.finoban.requisicao.BancoRequisicao;
import com.bandtec.br.finoban.requisicao.RequisicaoApisExternas;
import com.bandtec.br.finoban.resposta.RespostaApi;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@RestController
@RequestMapping("/api-finoban")
public class FinanciamentoApisExternasController {

    private final RestTemplate restTemplate;

    public FinanciamentoApisExternasController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }


    private ResponseEntity<RespostaApi> response;

    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Requisição de financiamento realizado com sucesso",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = RespostaApi.class)))
            ),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Não foi possível obter dados das apis.")
    })
//            content = @Content(schema = @Schema(implementation = RespostaApi.class)))
    @PostMapping("/financiamento")
    public ResponseEntity realizarFinaciamento(@RequestBody BancoRequisicao novaRequisicao){
        return ResponseEntity.status(200).body(RequisicaoApisExternas.requisicoesApisExternas(novaRequisicao));
    }

//    @PostMapping
//    public ResponseEntity createPost(@RequestBody BancoRequisicao novaRequisicao) {
//
//        if (retornaLista(novaRequisicao).isEmpty()) {
//            return ResponseEntity.status(400).build();
//        } else {
//            return ResponseEntity.status(200).body(retornaLista(novaRequisicao));
//        }
//
//    }

}

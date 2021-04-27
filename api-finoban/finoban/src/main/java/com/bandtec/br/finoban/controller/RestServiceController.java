package com.bandtec.br.finoban.controller;

import com.bandtec.br.finoban.requisicao.BancoRequisicao;
import com.bandtec.br.finoban.resposta.RespostaApi;
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
public class RestServiceController {

    private final RestTemplate restTemplate;

    public RestServiceController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }


    private ResponseEntity<RespostaApi> response;

    @PostMapping("/financiamento")
    public List<RespostaApi> retornaLista(@RequestBody BancoRequisicao novaRequisicao){

        List<String> listUrl = new ArrayList<>();
        List<RespostaApi> listResponse = new ArrayList<>();

        listUrl.add("http://18.207.233.50:3333/openbanking/v1/financiamento/");
        listUrl.add("http://18.207.233.50:5000/openbanking/v1/financiamento");
        listUrl.add("http://18.207.233.50:8000/openbanking/v1/financiamento");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        Map<String, Object> map = new HashMap<>();
        map.put("cnpj", novaRequisicao.getCnpj());
        map.put("renda", novaRequisicao.getRenda());
        map.put("valorImovel", novaRequisicao.getValorImovel());
        map.put("tempoFinanciamento", novaRequisicao.getTempoFinanciamento());

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

        for (int i = 0; i < listUrl.size(); i++){
            response = this.restTemplate.postForEntity(listUrl.get(i), entity, RespostaApi.class);
            RespostaApi bancoResponse = response.getBody();
            listResponse.add(bancoResponse);
        }

        return listResponse;

    }

    @PostMapping
    public ResponseEntity createPost(@RequestBody BancoRequisicao novaRequisicao) {

        if (retornaLista(novaRequisicao).isEmpty()) {
            return ResponseEntity.status(400).build();
        } else {
            return ResponseEntity.status(200).body(retornaLista(novaRequisicao));
        }

    }

}

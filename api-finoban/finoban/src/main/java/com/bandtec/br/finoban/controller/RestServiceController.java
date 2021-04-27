package com.bandtec.br.finoban.controller;

import com.bandtec.br.finoban.models.Banco;
import com.bandtec.br.finoban.models.Requisicao;
import com.bandtec.br.finoban.models.Resposta;
import org.aspectj.weaver.ast.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@RestController
@RequestMapping("/teste")
public class RestServiceController {

    private final RestTemplate restTemplate;

    public RestServiceController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    private ResponseEntity<Resposta> response;

    public List<Resposta> retornaLista(Requisicao novaRequisicao){

        List<String> listUrl = new ArrayList<>();
        List<Resposta> listResponse = new ArrayList<>();

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
            response = this.restTemplate.postForEntity(listUrl.get(i), entity, Resposta.class);
            Resposta bancoResponse = response.getBody();
            listResponse.add(bancoResponse);
        }

        return listResponse;

    }

    @PostMapping
    public ResponseEntity createPost(@RequestBody Requisicao novaRequisicao) {

        if (retornaLista(novaRequisicao).isEmpty()) {
            return ResponseEntity.status(400).build();
        } else {
            return ResponseEntity.status(200).body(retornaLista(novaRequisicao));
        }

    }

}

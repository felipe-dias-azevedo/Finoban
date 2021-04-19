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
public class RestService {

    private final RestTemplate restTemplate;

    public RestService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public List<String> listUrl = new ArrayList<>();
    public List<Resposta> listResponse = new ArrayList<>();
    private ResponseEntity<Resposta> response;

    public List<Resposta> retornaLista(Requisicao novaRequisicao){
        listUrl.add("http://18.207.233.50:3333/openbanking/v1/financiamento/");
        listUrl.add("http://18.207.233.50:5000/openbanking/v1/financiamento");


        // create headers
        HttpHeaders headers = new HttpHeaders();
        // set `content-type` header
        headers.setContentType(MediaType.APPLICATION_JSON);
        // set `accept` header
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        Map<String, Object> map = new HashMap<>();
        map.put("cnpj", novaRequisicao.getCnpj());
        map.put("renda", novaRequisicao.getRenda());
        map.put("valorImovel", novaRequisicao.getValorImovel());
        map.put("tempoFinanciamento", novaRequisicao.getTempoFinanciamento());

        // build the request
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

        for (int i = 0; i < listUrl.size(); i++){
            // send POST request
            response = this.restTemplate.postForEntity(
                    listUrl.get(i), entity, Resposta.class);

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

            return ResponseEntity.status(200).body(listResponse);
        }


    }

}

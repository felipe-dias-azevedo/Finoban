package com.bandtec.br.finoban.controller;

import com.bandtec.br.finoban.models.Resposta;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
@RequestMapping("/presil")
public class PresilController {
    private final RestTemplate restTemplate;

    public PresilController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();

    }

    @PostMapping
    public Resposta banco2() {

        String url = "http://18.207.233.50:8000/openbanking/v1/financiamento/";

        // create headers
        HttpHeaders headers = new HttpHeaders();
        // set `content-type` header
        headers.setContentType(MediaType.APPLICATION_JSON);
        // set `accept` header
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        // create a map for post parameters
        Map<String, Object> map = new HashMap<>();
        map.put("cnpj", 123);
        map.put("renda", 5000);
        map.put("valorImovel", 600000);
        map.put("tempoFinanciamento", 30);

        // build the request
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

        // send POST request
        ResponseEntity<Resposta> response = this.restTemplate.postForEntity(url, entity, Resposta.class);

        Resposta bancoResponse = response.getBody();

        List<Resposta> listResponse = new ArrayList<>();

        listResponse.add(bancoResponse);

        // check response status code
        if (response.getStatusCode() == HttpStatus.OK) {
            for (int i = 0; i < listResponse.size(); i++){
                System.out.println(listResponse.get(i).getData().getTaxaAdministracao());
                System.out.println(listResponse.get(i).getData().getTaxa());
                System.out.println(listResponse.get(i).getData().getDfi());
                System.out.println(listResponse.get(i).getData().getTaxaTotal());
            }
            return bancoResponse;
        } else {
            return null;
        }

    }
}

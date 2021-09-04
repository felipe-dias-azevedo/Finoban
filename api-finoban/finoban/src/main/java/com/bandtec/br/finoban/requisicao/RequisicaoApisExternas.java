package com.bandtec.br.finoban.requisicao;

import com.bandtec.br.finoban.resposta.RespostaApi;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class RequisicaoApisExternas {

    private static ResponseEntity<RespostaApi> response;

    public static List<RespostaApi> requisicoesApisExternas(BancoRequisicao requisicao) {
        List<String> listUrl = new ArrayList<>();
        List<RespostaApi> listResponse = new ArrayList<>();
        listUrl.add("http://54.172.194.145:3333/openbanking/v1/financiamento");
        listUrl.add("http://54.172.194.145:5000/openbanking/v1/financiamento");
        listUrl.add("http://54.172.194.145:8000/openbanking/v1/financiamento");
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        RestTemplate restTemplate = restTemplateBuilder.build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(Mapa(requisicao), headers);
        return requisicoesApisExternas(listUrl, listResponse,
                listUrl.size()-1, restTemplate, entity);
    }

    private static List<RespostaApi>
    requisicoesApisExternas(List<String> listaUrl,
                            List<RespostaApi> respostaApiList,
                            int indice,
                            RestTemplate restTemplate,
                            HttpEntity<Map<String, Object>> entity) {
        if (indice < 0) {
            return respostaApiList;
        } else {
            response = restTemplate.postForEntity(listaUrl.get(indice), entity, RespostaApi.class);
            RespostaApi bancoResponse = response.getBody();
            respostaApiList.add(bancoResponse);
            return requisicoesApisExternas(listaUrl, respostaApiList, indice-1, restTemplate, entity);
        }
    }

    private static Map<String, Object> Mapa(BancoRequisicao requisicao) {
        Map<String, Object> map = new HashMap<>();
        map.put("cnpj", requisicao.getCnpj());
        map.put("renda", requisicao.getRenda());
        map.put("valorImovel", requisicao.getValorImovel());
        map.put("tempoFinanciamento", requisicao.getTempoFinanciamento());
        return map;
    }
}

package com.bandtec.br.finoban.models;

import com.bandtec.br.finoban.demo.Taxavel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class BancoCifra implements Taxavel {

    public final static String URI_CIFRA = "http://localhost:8000/api-cifra/";

    public Banco banco;

    @Override
    public double getValorCet(Double valorImovel) {

        RestTemplate client = new RestTemplate();
//        ResponseEntity<Banco> response = client.
//                getForEntity(URI_CIFRA + valorImovel, Banco.class);

        ResponseEntity<Banco> responseEntity = client.getForEntity(URI_CIFRA + valorImovel, Banco.class);

        banco = responseEntity.getBody();

        banco.valorCet =  (banco.taxaJuros * banco.valorImovel)
                + banco.dfi + (banco.mip * banco.valorImovel + banco.taxaAdministracao);
        return banco.valorCet;
    }

    public Banco getBanco() {
        return banco;
    }
}

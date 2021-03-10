package com.bandtec.br.finoban.models;

import com.bandtec.br.finoban.demo.Taxavel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class BancoS16Bank implements Taxavel {
    public final static String URI_S16BANK = "http://localhost:7000/api-s16bank/";

    public Banco banco;

    public Banco getBanco() {
        return banco;
    }

    @Override
    public double getValorCet(Double valorImovel) {

        RestTemplate client = new RestTemplate();
//        ResponseEntity<Banco> response = client.
//                getForEntity(URI_CIFRA + valorImovel, Banco.class);

        ResponseEntity<Banco> responseEntity = client.getForEntity(URI_S16BANK + valorImovel, Banco.class);

        banco = responseEntity.getBody();

        banco.valorCet = (banco.taxaJuros * banco.valorImovel)
                + banco.dfi + (banco.mip * banco.valorImovel + banco.taxaAdministracao);
        return banco.valorCet;
    }
}

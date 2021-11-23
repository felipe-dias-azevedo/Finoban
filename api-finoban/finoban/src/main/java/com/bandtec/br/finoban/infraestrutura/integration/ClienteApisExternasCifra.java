package com.bandtec.br.finoban.infraestrutura.integration;

import com.bandtec.br.finoban.dominio.resposta.StatusHealthCkeck;
import com.bandtec.br.finoban.dominio.requisicao.BancoRequisicaoModel;
import com.bandtec.br.finoban.dominio.resposta.RespostaApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "apiexternacifra", url = "http://localhost:8008/")
public interface ClienteApisExternasCifra {

    @PostMapping("openbanking/v1/financiamento")
    RespostaApi postCifra(@RequestBody BancoRequisicaoModel bancoRequisicao);

    @GetMapping("health-check")
    StatusHealthCkeck getStatusCifra();
}

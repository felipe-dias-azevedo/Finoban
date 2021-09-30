package com.bandtec.br.finoban.infraestrutura.integration;

import com.bandtec.br.finoban.dominio.requisicao.BancoRequisicao;
import com.bandtec.br.finoban.dominio.resposta.RespostaApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "apiexternacifra", url = "http://54.172.194.145:8000/")
public interface ClienteApisExternasCifra {

    @PostMapping("openbanking/v1/financiamento")
    RespostaApi postCifra(@RequestBody BancoRequisicao bancoRequisicao);
}

package com.bandtec.br.finoban.infraestrutura.integration;

import com.bandtec.br.finoban.dominio.requisicao.BancoRequisicao;
import com.bandtec.br.finoban.dominio.resposta.RespostaApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "apiexternas16bank", url = "http://3.222.7.102:5000/")
public interface ClienteApisExternasS16Bank {

    @PostMapping("openbanking/v1/financiamento")
    RespostaApi postS16Bank(@RequestBody BancoRequisicao bancoRequisicao);
}

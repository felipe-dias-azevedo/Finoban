package com.bandtec.br.finoban.infraestrutura.integration;

import com.bandtec.br.finoban.dominio.requisicao.BancoRequisicaoModel;
import com.bandtec.br.finoban.dominio.resposta.RespostaApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "apiexterna16bank", url = "http://3.222.7.102:3333/")
public interface ClienteApisExternasPresil {

    @PostMapping("openbanking/v1/financiamento")
    RespostaApi postBancoPresil(@RequestBody BancoRequisicaoModel bancoRequisicao);
}

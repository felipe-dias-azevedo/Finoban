package com.bandtec.br.finoban.service.core.financiamento;

import com.bandtec.br.finoban.controller.CadastroController;
import com.bandtec.br.finoban.dominio.Data;
import com.bandtec.br.finoban.dominio.requisicao.BancoRequisicaoModel;
import com.bandtec.br.finoban.dominio.resposta.RespostaApi;
import com.bandtec.br.finoban.infraestrutura.integration.ClienteApisExternasS16Bank;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class FinanciamentoApiS16BankImpl {

    @Autowired
    private static ClienteApisExternasS16Bank clienteApisExternasS16Bank;
    private static final Logger log = LogManager.getLogger(FinanciamentoApiS16BankImpl.class.getName());

    public static RespostaApi postS16Bank(BancoRequisicaoModel bancoRequisicaoModel) {

        var dataMockadaParaErro = new Data();
        dataMockadaParaErro.setDfi(1.70);
        dataMockadaParaErro.setMip(1.90);
        dataMockadaParaErro.setTaxa(1.59);
        dataMockadaParaErro.setTaxaAdministracao(1.50);
        dataMockadaParaErro.setTaxaTotal(dataMockadaParaErro.getTaxa() +
                dataMockadaParaErro.getTaxaAdministracao() +
                dataMockadaParaErro.getDfi() + dataMockadaParaErro.getMip());

        try {
            var request = clienteApisExternasS16Bank.postS16Bank(bancoRequisicaoModel);
            log.info("Sucesso ao fazer requisição na API Cifra");
            log.info(bancoRequisicaoModel);
            return request;
        } catch (Exception ex) {
            log.error("Requisição a API externa Cifra");
            return new RespostaApi(false, 404, dataMockadaParaErro);
        }
    }
}

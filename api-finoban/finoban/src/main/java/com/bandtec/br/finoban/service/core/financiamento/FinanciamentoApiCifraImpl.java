package com.bandtec.br.finoban.service.core.financiamento;

import com.bandtec.br.finoban.controller.CadastroController;
import com.bandtec.br.finoban.dominio.Data;
import com.bandtec.br.finoban.dominio.requisicao.BancoRequisicaoModel;
import com.bandtec.br.finoban.dominio.resposta.RespostaApi;
import com.bandtec.br.finoban.infraestrutura.integration.ClienteApisExternasCifra;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor
public class FinanciamentoApiCifraImpl {

    @Autowired
    private static ClienteApisExternasCifra clienteApisExternasCifra;
    private static final Logger log = LogManager.getLogger(FinanciamentoApiCifraImpl.class.getName());

    public static RespostaApi postCifra(BancoRequisicaoModel bancoRequisicaoModel) {

        var dataMockadaParaErro = new Data();
        dataMockadaParaErro.setDfi(1.70);
        dataMockadaParaErro.setMip(1.90);
        dataMockadaParaErro.setTaxa(1.59);
        dataMockadaParaErro.setTaxaAdministracao(1.50);
        dataMockadaParaErro.setTaxaTotal(dataMockadaParaErro.getTaxa() +
                dataMockadaParaErro.getTaxaAdministracao() +
                dataMockadaParaErro.getDfi() + dataMockadaParaErro.getMip());

        try {
            var request = clienteApisExternasCifra.postCifra(bancoRequisicaoModel);
            log.info("Sucesso ao fazer requisição na API Cifra");
            log.info(bancoRequisicaoModel);
            return request;
        } catch (Exception ex) {
            log.error("Requisição a API externa Cifra");
            return new RespostaApi(false, 404, dataMockadaParaErro);
        }
    }
}

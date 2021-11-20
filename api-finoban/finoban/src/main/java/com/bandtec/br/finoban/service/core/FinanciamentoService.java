package com.bandtec.br.finoban.service.core;

import com.bandtec.br.finoban.controller.CadastroController;
import com.bandtec.br.finoban.dominio.requisicao.BancoRequisicaoModel;
import com.bandtec.br.finoban.infraestrutura.integration.ClienteApisExternasCifra;
import com.bandtec.br.finoban.infraestrutura.integration.ClienteApisExternasPresil;
import com.bandtec.br.finoban.infraestrutura.integration.ClienteApisExternasS16Bank;
import com.bandtec.br.finoban.dominio.Data;
import com.bandtec.br.finoban.dominio.resposta.RespostaApi;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class FinanciamentoService {

    private RespostaApi respostaApiCifra;
    private RespostaApi respostaApiS16Bank;
    private RespostaApi respostaApiPresil;
    private ClienteApisExternasCifra clienteCifra;
    private ClienteApisExternasS16Bank clienteS16Bank;
    private ClienteApisExternasPresil clientePresil;
    private static final Logger log = LogManager.getLogger(CadastroController.class.getName());

    public List<RespostaApi> RealizarRequisicaoApisExternas(BancoRequisicaoModel novaRequisicao) {

        Data dataMockadaParaErro = new Data();
        dataMockadaParaErro.setDfi(1.70);
        dataMockadaParaErro.setMip(1.90);
        dataMockadaParaErro.setTaxa(1.59);
        dataMockadaParaErro.setTaxaAdministracao(1.50);
        dataMockadaParaErro.setTaxaTotal((double) Math.round(dataMockadaParaErro.getTaxa() + dataMockadaParaErro.getTaxaAdministracao() + dataMockadaParaErro.getDfi() + dataMockadaParaErro.getMip()));

        try {
            respostaApiCifra = clienteCifra.postCifra(novaRequisicao);
            log.info("Sucesso ao fazer requisição na API Cifra");
            log.info(novaRequisicao);
        } catch (Exception ex) {
            log.error("Requisição a API externa Cifra");
            respostaApiCifra = new RespostaApi();
            respostaApiCifra.setStatus(404);
            respostaApiCifra.setData(dataMockadaParaErro);
        }

        try {
            respostaApiS16Bank = clienteS16Bank.postS16Bank(novaRequisicao);
            respostaApiS16Bank.getData().setTaxaTotal(respostaApiS16Bank.getData().getTaxaTotal() * 100);
            log.info("Sucesso ao fazer requisição na API S16 Bank");
            log.info(novaRequisicao);
        }catch (Exception ex) {
            log.error("Requisição a API externa S16 Bank");
            respostaApiS16Bank = new RespostaApi();
            respostaApiS16Bank.setStatus(404);
            respostaApiS16Bank.setData(dataMockadaParaErro);
        }

        try {
            respostaApiPresil = clientePresil.postBancoPresil(novaRequisicao);
            respostaApiPresil.getData().setTaxaTotal(respostaApiPresil.getData().getTaxaTotal() * 110);
            log.info("Sucesso ao fazer requisição na API Banco do Presil");
            log.info(novaRequisicao);
        } catch (Exception ex) {
            log.error("Requisição a API externa Banco do Presil");
            respostaApiPresil = new RespostaApi();
            respostaApiPresil.setStatus(404);
            respostaApiPresil.setData(dataMockadaParaErro);
        }

        return new ArrayList(Arrays.asList(respostaApiPresil, respostaApiS16Bank, respostaApiCifra));
    }
}

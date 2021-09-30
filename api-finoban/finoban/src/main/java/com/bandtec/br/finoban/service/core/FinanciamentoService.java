package com.bandtec.br.finoban.service.core;

import com.bandtec.br.finoban.infraestrutura.integration.ClienteApisExternasCifra;
import com.bandtec.br.finoban.infraestrutura.integration.ClienteApisExternasPresil;
import com.bandtec.br.finoban.infraestrutura.integration.ClienteApisExternasS16Bank;
import com.bandtec.br.finoban.dominio.Data;
import com.bandtec.br.finoban.dominio.requisicao.BancoRequisicao;
import com.bandtec.br.finoban.dominio.resposta.RespostaApi;
import lombok.AllArgsConstructor;
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

    public List<RespostaApi> RealizarRequisicaoApisExternas(BancoRequisicao novaRequisicao) {

        Data dataMockadaParaErro = new Data();
        dataMockadaParaErro.setDfi(1.70);
        dataMockadaParaErro.setMip(1.90);
        dataMockadaParaErro.setTaxa(1.59);
        dataMockadaParaErro.setTaxaAdministracao(1.50);
        dataMockadaParaErro.setTaxaTotal(dataMockadaParaErro.getTaxa() + dataMockadaParaErro.getTaxaAdministracao() + dataMockadaParaErro.getDfi() + dataMockadaParaErro.getMip());

        try {
            respostaApiCifra = clienteCifra.postCifra(novaRequisicao);
        } catch (Exception ex) {
            respostaApiCifra = new RespostaApi();
            respostaApiCifra.setStatus(404);
            respostaApiCifra.setData(dataMockadaParaErro);
        }

        try {
            respostaApiS16Bank = clienteS16Bank.postS16Bank(novaRequisicao);
        }catch (Exception ex) {
            respostaApiS16Bank = new RespostaApi();
            respostaApiS16Bank.setStatus(404);
            respostaApiS16Bank.setData(dataMockadaParaErro);
        }

        try {
            respostaApiPresil = clientePresil.postBancoPresil(novaRequisicao);
        } catch (Exception ex) {
            respostaApiPresil = new RespostaApi();
            respostaApiPresil.setStatus(404);
            respostaApiPresil.setData(dataMockadaParaErro);
        }

        return new ArrayList(Arrays.asList(respostaApiPresil, respostaApiS16Bank, respostaApiCifra));
    }
}

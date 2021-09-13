package com.bandtec.br.finoban.controller;

import com.bandtec.br.finoban.integracao.ClienteApisExternasCifra;
import com.bandtec.br.finoban.integracao.ClienteApisExternasPresil;
import com.bandtec.br.finoban.integracao.ClienteApisExternasS16Bank;
import com.bandtec.br.finoban.models.Data;
import com.bandtec.br.finoban.requisicao.BancoRequisicao;
import com.bandtec.br.finoban.resposta.RespostaApi;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Arrays;

@Service
@RestController
@RequestMapping("/api-finoban")
public class FinanciamentoApisExternasController {

    @Autowired
    private ClienteApisExternasCifra clienteCifra;

    @Autowired
    private ClienteApisExternasS16Bank clienteS16Bank;

    @Autowired
    private ClienteApisExternasPresil clientePresil;

    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Requisição de financiamento realizado com sucesso",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = RespostaApi.class)))
            ),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Não foi possível obter dados das apis.")
    })
    @PostMapping("/financiamento")
    public ResponseEntity realizarFinaciamento(@RequestBody BancoRequisicao novaRequisicao){

        RespostaApi respostaApiCifra;
        RespostaApi respostaApiS16Bank;
        RespostaApi respostaApiPresil;

        Data data = new Data();
        data.setDfi(1.70);
        data.setMip(1.90);
        data.setTaxa(1.59);
        data.setTaxaAdministracao(1.50);
        data.setTaxaTotal(data.getTaxa() + data.getTaxaAdministracao() + data.getDfi() + data.getMip());

        try {
            respostaApiCifra = clienteCifra.postCifra(novaRequisicao);
        } catch (Exception ex) {
            respostaApiCifra = new RespostaApi();
            respostaApiCifra.setStatus(404);
            respostaApiCifra.setData(data);
        }

        try {
            respostaApiS16Bank = clienteS16Bank.postS16Bank(novaRequisicao);
        }catch (Exception ex) {
            respostaApiS16Bank = new RespostaApi();
            respostaApiS16Bank.setStatus(404);
            respostaApiS16Bank.setData(data);
        }

        try {
            respostaApiPresil = clientePresil.postBancoPresil(novaRequisicao);
        } catch (Exception ex) {
            respostaApiPresil = new RespostaApi();
            respostaApiPresil.setStatus(404);
            respostaApiPresil.setData(data);
        }

        return ResponseEntity.status(200).body(new ArrayList<RespostaApi>(Arrays.asList(respostaApiPresil, respostaApiS16Bank, respostaApiCifra)));
    }

}

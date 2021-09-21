package com.bandtec.br.finoban.controller;

import com.bandtec.br.finoban.integracao.ClienteApisExternasCifra;
import com.bandtec.br.finoban.integracao.ClienteApisExternasPresil;
import com.bandtec.br.finoban.integracao.ClienteApisExternasS16Bank;
import com.bandtec.br.finoban.models.Data;
import com.bandtec.br.finoban.requisicao.BancoRequisicao;
import com.bandtec.br.finoban.resposta.ResponseGeneric;
import com.bandtec.br.finoban.resposta.RespostaApi;
import com.bandtec.br.finoban.service.core.FinanciamentoService;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
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

@RestController
@RequestMapping("/api-finoban")
@AllArgsConstructor
public class FinanciamentoApisExternasController {

    private FinanciamentoService financiamentoService;

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
        return ResponseEntity.status(200).body(new ResponseGeneric(financiamentoService.RealizarRequisicaoApisExternas(novaRequisicao)));
    }

}

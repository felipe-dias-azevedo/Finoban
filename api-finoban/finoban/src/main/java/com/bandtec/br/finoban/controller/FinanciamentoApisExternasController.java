package com.bandtec.br.finoban.controller;

import com.bandtec.br.finoban.requisicao.BancoRequisicao;
import com.bandtec.br.finoban.requisicao.RequisicaoApisExternas;
import com.bandtec.br.finoban.resposta.RespostaApi;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Service
@RestController
@RequestMapping("/api-finoban")
public class FinanciamentoApisExternasController {

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
        return ResponseEntity.status(200).body(RequisicaoApisExternas.requisicoesApisExternas(novaRequisicao));
    }

}

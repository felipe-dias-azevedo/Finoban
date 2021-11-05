package com.bandtec.br.finoban.controller;

import com.bandtec.br.finoban.dominio.entidades.Regiao;
import com.bandtec.br.finoban.dominio.resposta.SingleResponse;
import com.bandtec.br.finoban.service.core.GestaoRegioesService;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api-finoban/regioes")
@AllArgsConstructor
public class RegiaoController {


    private GestaoRegioesService gestaoRegioesService;

    @GetMapping
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Regiões resgatadas com sucesso",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = Regiao.class)))
                    ),
                    @ApiResponse(
                            responseCode = "204",
                            description = "Não encontramos nenhuma região"
                    )
            }
    )
    public ResponseEntity getRegioes() {
        List<Regiao> regioes = gestaoRegioesService.listarRegioes();
        if (regioes.isEmpty())
            return ResponseEntity.status(204).build();

        return ResponseEntity.status(200).body(new SingleResponse(regioes));
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Requisição feita com sucesso",
                    content = @Content(schema = @Schema(implementation = Regiao.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Região não encontrada para resgatar"
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity getRegiao(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(new SingleResponse<>(gestaoRegioesService.resgatarRegiaoPeloId(id)));
    }


    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Região registrada com sucesso"
            )
    })
    @PostMapping
    public ResponseEntity postRegiao(@RequestBody Regiao regiao) {
        gestaoRegioesService.registrarRegiao(regiao);
        return ResponseEntity.status(201).build();
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Região deletada com sucesso"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Região não encontrada para excluir"
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity deleteRegiao(@PathVariable int id) {
        gestaoRegioesService.deletarRegiaoPeloId(id);
        return ResponseEntity.status(204).build();
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Região atualizada com sucesso",
                    content = @Content(schema = @Schema(implementation = SingleResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Região não encontrada para atualizar"
            )
    })
    @PutMapping
    public ResponseEntity atualizarRegiao(@RequestBody Regiao regiao) {
        return ResponseEntity.status(200).body(new SingleResponse<>(gestaoRegioesService.atualizarRegiao(regiao)));
    }
}

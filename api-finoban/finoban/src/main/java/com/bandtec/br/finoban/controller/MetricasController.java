package com.bandtec.br.finoban.controller;

import com.bandtec.br.finoban.dominio.MetricaModel;
import com.bandtec.br.finoban.dominio.entidades.Metricas;
import com.bandtec.br.finoban.dominio.entidades.Regiao;
import com.bandtec.br.finoban.dominio.resposta.SingleResponse;
import com.bandtec.br.finoban.service.core.MetricasService;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api-finoban/metricas")
public class MetricasController {

    private MetricasService metricasService;

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = MetricaModel.class)))
            )
    })
    @GetMapping
    public ResponseEntity obterTodasMetricas() {
        var listaMetricas = this.metricasService.obterTodasMetricas();
        if (listaMetricas.isEmpty())
            return ResponseEntity.status(204).build();

        return ResponseEntity.status(200).body(new SingleResponse<>(listaMetricas));
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = @Content(schema = @Schema(implementation = MetricaModel.class))
            ),
    })
    @GetMapping("/{id}")
    public ResponseEntity obterMetricaPeloId(@PathVariable int id) {
        return ResponseEntity.status(200).body(new SingleResponse<>(this.metricasService.obterMetrica(id)));
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Metrica postada com sucesso"
            ),
    })
    @PostMapping
    public ResponseEntity postarMetrica(@RequestBody MetricaModel metricas) {
        this.metricasService.postarMetrica(metricas);
        return ResponseEntity.status(201).build();
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Metrica deletada com sucesso"
            ),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity deletarMetrica(@PathVariable int id) {
        this.metricasService.deletarMetrica(id);
        return ResponseEntity.status(204).build();
    }
}

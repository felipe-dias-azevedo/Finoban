package com.bandtec.br.finoban.controller;

import com.bandtec.br.finoban.dominio.resposta.SingleResponse;
import com.bandtec.br.finoban.service.HealthCheckApisExternasService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health-check/apis-externas")
@AllArgsConstructor
public class HealthCheckApisExternasController {

    private HealthCheckApisExternasService healthCheckApisExternasService;

    @GetMapping
    public ResponseEntity getStatusHealthCheckApisExternas() {
        return ResponseEntity.status(200).body(new SingleResponse<>(this.healthCheckApisExternasService.obterStatusHealthCheckApisExternas()));
    }
}

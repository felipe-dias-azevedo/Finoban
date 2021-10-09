package com.bandtec.br.finoban.controller;

import com.bandtec.br.finoban.dominio.resposta.DashboardDTO;
import com.bandtec.br.finoban.service.AuthService;
import com.bandtec.br.finoban.service.DashboardService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api-finoban/dashboard")
@AllArgsConstructor
public class DashboardController {

    private AuthService authService;

    private DashboardService dashboardService;

    @GetMapping
    public ResponseEntity<DashboardDTO> getDataDashboard(@RequestHeader("Authorization") String authorization) {

        authService.validateJwt(authorization);

        DashboardDTO dadosDashboard = dashboardService.obterDadosDashboard();

        return ResponseEntity.status(200).body(dadosDashboard);
    }

}

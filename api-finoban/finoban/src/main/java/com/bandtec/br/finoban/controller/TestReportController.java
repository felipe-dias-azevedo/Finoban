package com.bandtec.br.finoban.controller;

import com.bandtec.br.finoban.dominio.resposta.*;
import com.bandtec.br.finoban.service.TestReportService;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/api-finoban/tests")
@AllArgsConstructor
public class TestReportController {

    private TestReportService testReportService;

    @GetMapping
    public ResponseEntity<List<TestReportDTO>> getTestsReports()
    {
        List<TestReportDTO> reports = testReportService.obterTestes();
        if (reports.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(reports);
    }

    @GetMapping("/domains")
    public ResponseEntity<List<TestReportDomainSpecificDTO>> getTestsDomains()
    {
        List<TestReportDomainSpecificDTO> reports =
                testReportService.obterTestesPorDominioEspecifico(testReportService.obterTestes());
        if (reports.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(reports);
    }

    @GetMapping("/apps")
    public ResponseEntity<List<TestReportAppsDTO>> getTestsApps()
    {
        List<TestReportAppsDTO> reports = testReportService.obterTestesPorApps(testReportService.obterTestes());
        if (reports.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(reports);
    }

    @GetMapping("/apps/{id}")
    public ResponseEntity<List<TestReportAppSpecificDTO>> getTestsAppsSpecific(@PathVariable Integer id)
    {
        List<TestReportAppSpecificDTO> reports = testReportService.obterTestesPorAppEspecifico(
                testReportService.obterTestes());
        if (id != 0) {
            return ResponseEntity.status(404).build();
        } else if (reports.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(reports);
    }

    @GetMapping("/dashboard")
    public ResponseEntity<TestsDashboardDTO> getTestsDashboard()
    {
        var testes = testReportService.obterTestes();
        TestsDashboardDTO dataDashboard = testReportService.obterTestesPorAnalytics(testes);
        if (dataDashboard == null) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(dataDashboard);
    }

    @PostMapping
    public ResponseEntity<?> postRodarTests()
    {
        /*
         * TODO: Cenário em que o usuário solicita execução e armazena em uma lista o processo para checar se foi finalizado
         * Se ainda não foi finalizado retornar STATUS 102, e assim que finalizar o processo, retirar o item da lista.
         */
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
        service.submit(() -> {
            try {
                testReportService.atualizarTestes();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return ResponseEntity.status(202).build();
    }
}

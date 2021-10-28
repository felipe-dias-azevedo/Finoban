package com.bandtec.br.finoban.controller;

import com.bandtec.br.finoban.dominio.resposta.TestReportDTO;
import com.bandtec.br.finoban.service.TestReportService;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping
    public ResponseEntity<?> postRodarTests()
    {
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

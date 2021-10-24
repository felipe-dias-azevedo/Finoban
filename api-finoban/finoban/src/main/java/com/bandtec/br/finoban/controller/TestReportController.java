package com.bandtec.br.finoban.controller;

import com.bandtec.br.finoban.dominio.resposta.TestReportDTO;
import com.bandtec.br.finoban.service.TestReportService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
            return ResponseEntity.status(201).build();
        }
        return ResponseEntity.status(200).body(reports);
    }

    @PostMapping
    public ResponseEntity postRodarTests() {
        return ResponseEntity.status(503).build();
    }
}

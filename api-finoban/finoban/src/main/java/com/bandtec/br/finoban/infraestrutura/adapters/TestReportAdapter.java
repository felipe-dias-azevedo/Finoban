package com.bandtec.br.finoban.infraestrutura.adapters;

import com.bandtec.br.finoban.dominio.DAO.TestReportDAO;
import com.bandtec.br.finoban.dominio.resposta.TestReportDTO;

import java.util.List;
import java.util.stream.Collectors;

public class TestReportAdapter {

    public static List<TestReportDTO> testReportsAccesstoTransfer(List<TestReportDAO> testReports)
    {
        return testReports.stream()
                .map(TestReportDTO::new)
                .collect(Collectors.toList());
    }
}

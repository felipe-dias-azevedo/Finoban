package com.bandtec.br.finoban.service;

import com.bandtec.br.finoban.dominio.DAO.TestReportDAO;
import com.bandtec.br.finoban.dominio.resposta.TestReportDTO;
import com.bandtec.br.finoban.infraestrutura.helpers.CsvHelper;
import com.bandtec.br.finoban.infraestrutura.adapters.TestReportAdapter;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestReportService {

    private static final String TEST_REPORT_FILENAME = "testresult.csv";

    public List<TestReportDTO> obterTestes()
    {
        CsvHelper<TestReportDAO> csvHelper = new CsvHelper<>();
        List<TestReportDAO> testReports = csvHelper.read(TEST_REPORT_FILENAME, TestReportDAO.class);

        List<TestReportDTO> reportsList = TestReportAdapter.testReportsAccesstoTransfer(testReports);
        return reportsList == null ?
                TestReportAdapter.testReportsAccesstoTransfer(
                        csvHelper.read(
                                String.format("%s.backup", TEST_REPORT_FILENAME),
                                TestReportDAO.class))
                : reportsList;
    }

    public void inserirTestes(List<TestReportDTO> testReports) {

    }
}

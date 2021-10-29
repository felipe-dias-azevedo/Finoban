package com.bandtec.br.finoban.infraestrutura.adapters;

import com.bandtec.br.finoban.dominio.TestReport;
import com.bandtec.br.finoban.dominio.DAO.TestReportDAO;
import com.bandtec.br.finoban.dominio.resposta.TestReportAppsDTO;
import com.bandtec.br.finoban.dominio.resposta.TestReportDTO;

import java.util.List;
import java.util.stream.Collectors;

public class TestReportAdapter {

    public static List<TestReportDTO> testReportsAccesstoTransfer(List<TestReportDAO> testReports)
    {
        return testReports.stream()
                .map(TestReportAdapter::testReportDAOToDTO)
                .collect(Collectors.toList());
    }
    
    public static TestReportDTO testReportDAOToDTO(TestReportDAO testReportDAO) {
        TestReportDTO testReportDTO = new TestReportDTO();
        testReportDTO.setNomeClasse(testReportDAO.getNomeClasse());
        testReportDTO.setNomeTeste(testReportDAO.getNomeTeste());
        testReportDTO.setStatus(testReportDAO.getStatus());
        testReportDTO.setEstagio(testReportDAO.getEstagio());
        testReportDTO.setDuracao(Integer.valueOf(testReportDAO.getDuracao()));
        testReportDTO.setDataInsercao(testReportDAO.getDataInsercao());
        return testReportDTO;
    }

    public static TestReportDAO allureResultToTestReport(TestReport allureResult, String dataInsercao)
    {
        TestReportDAO testReport = new TestReportDAO();
        testReport.setNomeTeste(allureResult.getName());
        testReport.setStatus(allureResult.getStatus());
        testReport.setEstagio(allureResult.getStage());
        testReport.setDuracao(String.valueOf(allureResult.getStop() - allureResult.getStart()));
        testReport.setNomeClasse(allureResult.getFullName().replace("com.bandtec.br.finoban.", ""));
        testReport.setDataInsercao(dataInsercao);
        return testReport;
    }

//    public static TestReportAppsDTO testReportToApps(TestReportDTO testReport)
//    {
//
//    }
}

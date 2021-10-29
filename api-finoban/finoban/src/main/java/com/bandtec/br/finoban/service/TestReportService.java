package com.bandtec.br.finoban.service;

import com.bandtec.br.finoban.dominio.DAO.TestReportDAO;
import com.bandtec.br.finoban.dominio.TestReport;
import com.bandtec.br.finoban.dominio.enums.TestStatusGeralEnum;
import com.bandtec.br.finoban.dominio.resposta.TestReportAppsDTO;
import com.bandtec.br.finoban.dominio.resposta.TestReportDTO;
import com.bandtec.br.finoban.infraestrutura.helpers.*;
import com.bandtec.br.finoban.infraestrutura.adapters.TestReportAdapter;

import org.apache.commons.exec.ExecuteException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TestReportService {

    private static final String TEST_REPORT_FILENAME = "testresult.csv";
    private static final String MAVEN_TEST_COMMAND = "mvn test";

    public List<TestReportDTO> obterTestes()
    {
        CsvHelper<TestReportDAO> csvHelper = new CsvHelper<>();

        try {
            return TestReportAdapter.testReportsAccesstoTransfer(
                    csvHelper.read(TEST_REPORT_FILENAME, TestReportDAO.class)
            );
        } catch (NoSuchFileException e) {
            System.out.println("[FINOBAN] Reading CSV Backup Tests Report");
            try {
                return TestReportAdapter.testReportsAccesstoTransfer(
                        csvHelper.read(
                                String.format("%s.backup", TEST_REPORT_FILENAME),
                                TestReportDAO.class));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public void atualizarTestes() throws IOException
    {
        String dateNow = DateHelper.obterDataAgoraPadraoISO8601();

        File[] oldReports = Paths.get("target", "allure-results").toFile().listFiles();
        Arrays.stream(Objects.requireNonNull(oldReports))
                .filter(oldReport -> oldReport.getName().endsWith("result.json"))
                .forEach(File::delete);

        try {
            ProcessHelper.executarComando(MAVEN_TEST_COMMAND);
        } catch (ExecuteException e) {
            System.out.println(e.getMessage());
        }

        File allureReportsFolder = Paths.get("target", "allure-results").toFile();

        if (allureReportsFolder.listFiles() == null) {
            throw new IOException();
        }

        JsonHelper<TestReport> jsonHelper = new JsonHelper<>();

        List<TestReportDAO> testResults = Arrays
                .stream(Objects.requireNonNull(allureReportsFolder.listFiles()))
                .filter(report -> !report.getName().endsWith("container.json"))
                .map(report -> TestReportAdapter.allureResultToTestReport(
                        jsonHelper.safeSerializeFromFile(report, TestReport.class),
                        dateNow))
                .collect(Collectors.toList());

        CsvHelper<TestReportDAO> csvHelper = new CsvHelper<>();
        csvHelper.write(TEST_REPORT_FILENAME, testResults);
    }

    public void inserirTestes(List<TestReportDTO> testReports) {

    }

    /**
     * Recebe os testes e gera um relatorio geral dos testes por "dominio" de teste.
     *
     * @param testes bateria de testes obtida do CSV de report do allure.
     */
    public List<TestReportAppsDTO> obterTestesPorApps(List<TestReportDTO> testes)
    {
        List<TestReportAppsDTO> testesPorApps = new ArrayList<>();

        TestReportAppsDTO app = new TestReportAppsDTO();
        List<TestReportDTO> testesConcluidos = testes.stream()
                .filter(teste -> teste.getStatus().equals("passed"))
                .collect(Collectors.toList());

        app.setNomeAplicacao("API Finoban");
        app.setDataExecucao(testes.get(0).getDataInsercao());
        app.setStatusGeral(testesConcluidos.stream()
                .findAny()
                .isPresent()
                ? TestStatusGeralEnum.PASSOU
                : TestStatusGeralEnum.FALHOU);
        app.setDuracaoExecucao(NumberHelper.valueOf(testes.stream()
                .mapToInt(TestReportDTO::getDuracao)
                .sum()) / 100);

        double sucessPercentage = NumberHelper.valueOf(testesConcluidos.size()) / NumberHelper.valueOf(testes.size());
        app.setPorcentagemSucesso(NumberHelper.round(sucessPercentage,2));
        testesPorApps.add(app);
        return testesPorApps;
    }
}

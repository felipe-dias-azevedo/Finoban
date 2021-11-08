package com.bandtec.br.finoban.service;

import com.bandtec.br.finoban.dominio.DAO.TestReportDAO;
import com.bandtec.br.finoban.dominio.TestReport;
import com.bandtec.br.finoban.dominio.enums.TestStatusGeralEnum;
import com.bandtec.br.finoban.dominio.resposta.*;
import com.bandtec.br.finoban.infraestrutura.adapters.TestsDashboardAdapter;
import com.bandtec.br.finoban.infraestrutura.helpers.*;
import com.bandtec.br.finoban.infraestrutura.adapters.TestReportAdapter;

import org.apache.commons.exec.ExecuteException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TestReportService {

    private static final String TEST_REPORT_FILENAME = "testresult.csv";
    private static final String MAVEN_TEST_COMMAND = "mvn test";
    private List<TestReportDTO> actualTestReports;
    private BasicFileAttributes actualAttributes;

    public List<TestReportDTO> obterTestes()
    {
        CsvHelper<TestReportDAO> csvHelper = new CsvHelper<>();

        if (actualAttributes != null && !actualTestReports.isEmpty()) {
            try {
                var attributes = FileHelper.getAttributes(FileHelper.getFilePath(TEST_REPORT_FILENAME));
                if (DateHelper.converterDataPadraoISO8601(attributes.creationTime()).
                        equals(DateHelper.converterDataPadraoISO8601(actualAttributes.creationTime()))
                        && attributes.size() == actualAttributes.size())
                {
                    return actualTestReports;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            actualTestReports = TestReportAdapter.testReportsAccessToTransfer(
                    csvHelper.read(TEST_REPORT_FILENAME, TestReportDAO.class));
            actualAttributes = FileHelper.getAttributes(FileHelper.getFilePath(TEST_REPORT_FILENAME));
            return actualTestReports;
        } catch (NoSuchFileException e) {
            System.out.println("[FINOBAN] Reading CSV Backup Tests Report");
            try {
                actualTestReports = TestReportAdapter.testReportsAccessToTransfer(
                        csvHelper.read(
                                String.format("%s.backup", TEST_REPORT_FILENAME),
                                TestReportDAO.class));
                actualAttributes = FileHelper.getAttributes(FileHelper.getFilePath(
                        String.format("%s.backup", TEST_REPORT_FILENAME)));
                return actualTestReports;
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

        app.setNomeAplicacao("API Finoban");
        app.setQuantidadeTestes(testes.size());
        app.setDataExecucao(testes.get(0).getDataInsercao());
        app.setStatusGeral(testes.stream()
                .anyMatch(teste -> teste.getStatus().equals("failed"))
                ? TestStatusGeralEnum.FALHOU
                : TestStatusGeralEnum.PASSOU);
        app.setDuracaoExecucao(NumberHelper.valueOf(testes.stream()
                .mapToInt(TestReportDTO::getDuracao)
                .sum()) / 100);
        double porcentagemSucesso = NumberHelper.valueOf((int) testes.stream()
                .filter(teste -> teste.getStatus().equals("passed")).count()) / NumberHelper.valueOf(testes.size());
        app.setPorcentagemSucesso(NumberHelper.round(porcentagemSucesso,2));

        testesPorApps.add(app);
        return testesPorApps;
    }

    public List<TestReportAppSpecificDTO> obterTestesPorAppEspecifico(List<TestReportDTO> testes)
    {
        var testesAppEspecifico = new ArrayList<TestReportAppSpecificDTO>();
        var classesInserted = new ArrayList<String>();
        String actualClass;

        for (TestReportDTO test : testes)
        {
            actualClass = TestReportAdapter.classNameHandle(test.getNomePackage());
            if (!classesInserted.contains(actualClass))
            {
                TestReportAppSpecificDTO appSpecific = new TestReportAppSpecificDTO();
                appSpecific.setQuantidadeFuncoes(0);
                appSpecific.setDuracaoExecucao(0.0);
                appSpecific.setPorcentagemSucesso(0.0);
                appSpecific.setNomeClasseTeste(actualClass);
                classesInserted.add(actualClass);
                for (TestReportDTO t : testes)
                {
                    if (actualClass.equals(TestReportAdapter.classNameHandle(t.getNomePackage())))
                    {
                        appSpecific.setQuantidadeFuncoes(appSpecific.getQuantidadeFuncoes() + 1);
                        appSpecific.setPorcentagemSucesso(
                                appSpecific.getPorcentagemSucesso() + (t.getStatus().equals("passed") ? 1 : 0));
                        appSpecific.setDuracaoExecucao(
                                appSpecific.getDuracaoExecucao() + (NumberHelper.valueOf(t.getDuracao()) / 100));
                    }
                }
                appSpecific.setDuracaoExecucao(NumberHelper.round(appSpecific.getDuracaoExecucao(), 2));
                appSpecific.setPorcentagemSucesso(NumberHelper.round(
                        appSpecific.getPorcentagemSucesso() / appSpecific.getQuantidadeFuncoes(),
                        2));
                testesAppEspecifico.add(appSpecific);
            }
        }

        return testesAppEspecifico;
    }

    public List<TestReportDomainSpecificDTO> obterTestesPorDominioEspecifico(List<TestReportDTO> testes)
    {
        var testsSpecificDomain = new ArrayList<TestReportDomainSpecificDTO>();
        var domainsInserted = new ArrayList<String>();
        String actualDomain;

        for (TestReportDTO test : testes)
        {
            actualDomain = TestReportAdapter.domainNameHandle(test.getNomePackage());
            if (!domainsInserted.contains(actualDomain) && !actualDomain.equals(""))
            {
                TestReportDomainSpecificDTO domainSpecific = new TestReportDomainSpecificDTO();
                domainSpecific.setQuantidadeFuncoes(0);
                domainSpecific.setDuracaoExecucao(0.0);
                domainSpecific.setPorcentagemSucesso(0.0);
                domainSpecific.setNomeDominioTeste(actualDomain);
                domainsInserted.add(actualDomain);
                for (TestReportDTO t : testes)
                {
                    if (actualDomain.equals(TestReportAdapter.domainNameHandle(t.getNomePackage())))
                    {
                        domainSpecific.setQuantidadeFuncoes(domainSpecific.getQuantidadeFuncoes() + 1);
                        domainSpecific.setPorcentagemSucesso(
                                domainSpecific.getPorcentagemSucesso() + (t.getStatus().equals("passed") ? 1 : 0));
                        domainSpecific.setDuracaoExecucao(
                                domainSpecific.getDuracaoExecucao() + (NumberHelper.valueOf(t.getDuracao()) / 100));
                    }
                }
                domainSpecific.setDuracaoExecucao(NumberHelper.round(domainSpecific.getDuracaoExecucao(), 2));
                domainSpecific.setPorcentagemSucesso(NumberHelper.round(
                        domainSpecific.getPorcentagemSucesso() / domainSpecific.getQuantidadeFuncoes(),
                        2));
                testsSpecificDomain.add(domainSpecific);
            }
        }
        return testsSpecificDomain;
    }

    public TestsDashboardDTO obterTestesPorAnalytics(List<TestReportDTO> testes)
    {
        return new TestsDashboardAdapter(testes).testsToDashboardDTO();
    }
}

package com.bandtec.br.finoban.infraestrutura.adapters;

import com.bandtec.br.finoban.dominio.enums.TestStatusGeralEnum;
import com.bandtec.br.finoban.dominio.resposta.TestReportAppSpecificDTO;
import com.bandtec.br.finoban.dominio.resposta.TestReportDTO;
import com.bandtec.br.finoban.dominio.resposta.TestReportDomainSpecificDTO;
import com.bandtec.br.finoban.dominio.resposta.TestsDashboardDTO;
import com.bandtec.br.finoban.infraestrutura.helpers.NumberHelper;
import com.bandtec.br.finoban.service.TestReportService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TestsDashboardAdapter {

    private final List<TestReportDTO> testes;
    private final TestReportService testService;

    public TestsDashboardAdapter(List<TestReportDTO> testes) {
        this.testService = new TestReportService();
        this.testes = testes;
    }

    public TestsDashboardDTO testsToDashboardDTO()
    {
        TestsDashboardDTO dashboardDTO = new TestsDashboardDTO();
        dashboardDTO.setPorcentagemSucessoPorClasse(this.testsToPorcentagemSucessoPorClasse());
        dashboardDTO.setPorcentagemGeralSucesso(this.testsToPorcentagemGeralSucesso());
        dashboardDTO.setQuantidadeTestes(this.testsToQuantidadeTestes());
        dashboardDTO.setUltimaDataExecucao(this.testsToUltimaDataExecucao());
        dashboardDTO.setDuracaoExecucao(this.testsToDuracaoExecucao());
        dashboardDTO.setStatusGeralTestes(this.testsToStatusGeralTestes());
        dashboardDTO.setPorcentagemSucessoPorDominio(this.testsToPorcentagemSucessoPorDominio());
        dashboardDTO.setTempoMedioExecucaoPorDominio(this.testsToTempoMedioExecucaoPorDominio());
        dashboardDTO.setTempoMedioExecucaoPorClasse(this.testsToTempoMedioExecucaoPorClasse());
        return dashboardDTO;
    }

    public Double testsToPorcentagemGeralSucesso()
    {
        return NumberHelper.round(NumberHelper.valueOf(
                (int) testes.stream()
                .map(TestReportDTO::getStatus)
                .filter(teste -> teste.equals("passed"))
                .count()) / this.testsToQuantidadeTestes(), 2);
    }

    public Integer testsToQuantidadeTestes()
    {
        return testes.size();
    }

    public String testsToUltimaDataExecucao()
    {
        return testes.stream()
                .filter(Objects::nonNull)
                .findAny()
                .map(TestReportDTO::getDataInsercao)
                .orElse(null);
    }

    public Double testsToDuracaoExecucao()
    {
        return NumberHelper.round(testes.stream()
                .map(TestReportDTO::getDuracao)
                .map(NumberHelper::valueOf)
                .map(duracao -> duracao / 100)
                .reduce(Double::sum)
                .orElse(0.0), 2);
    }

    public TestStatusGeralEnum testsToStatusGeralTestes()
    {
        return testes.stream()
                .map(TestReportDTO::getStatus)
                .anyMatch(teste -> teste.equals("failed"))
                ? TestStatusGeralEnum.FALHOU
                : TestStatusGeralEnum.PASSOU;
    }

    public List<List<Object>> testsToPorcentagemSucessoPorDominio()
    {
        List<TestReportDomainSpecificDTO> testsDomainSpecific = this.testService.obterTestesPorDominioEspecifico(this.testes);
        List<List<Object>> sucessoPorDominio = new ArrayList<>();

        sucessoPorDominio.add(List.of("Nome do domínio", "Porcentagem de sucesso"));

        testsDomainSpecific.stream()
                .<List<Object>>map(appSpecific -> Arrays.asList(
                        appSpecific.getNomeDominioTeste(),
                        appSpecific.getPorcentagemSucesso() * 100))
                .forEach(sucessoPorDominio::add);

        return sucessoPorDominio;
    }

    public List<List<Object>> testsToTempoMedioExecucaoPorDominio()
    {
        List<TestReportDomainSpecificDTO> testsDomainSpecific = this.testService.obterTestesPorDominioEspecifico(this.testes);
        List<List<Object>> execucaoPorDominio = new ArrayList<>();

        execucaoPorDominio.add(List.of("Nome do domínio", "Duração da execução"));

        testsDomainSpecific.stream()
                .<List<Object>>map(appSpecific -> Arrays.asList(
                        appSpecific.getNomeDominioTeste(),
                        appSpecific.getDuracaoExecucao()))
                .forEach(execucaoPorDominio::add);

        return execucaoPorDominio;
    }

    public List<List<Object>> testsToPorcentagemSucessoPorClasse()
    {
        List<TestReportAppSpecificDTO> testsAppSpecific = this.testService.obterTestesPorAppEspecifico(this.testes);
        List<List<Object>> sucessoPorClasse = new ArrayList<>();

        sucessoPorClasse.add(List.of("Nome da classe", "Porcentagem de sucesso"));

        testsAppSpecific.stream()
                .<List<Object>>map(appSpecific -> Arrays.asList(
                        appSpecific.getNomeClasseTeste(),
                        appSpecific.getPorcentagemSucesso() * 100))
                .forEach(sucessoPorClasse::add);

        return sucessoPorClasse;
    }

    public List<List<Object>> testsToTempoMedioExecucaoPorClasse()
    {
        List<TestReportAppSpecificDTO> testsAppSpecific = this.testService.obterTestesPorAppEspecifico(this.testes);
        List<List<Object>> execucaoPorClasse = new ArrayList<>();

        execucaoPorClasse.add(List.of("Nome da classe", "Duração da execução"));

        testsAppSpecific.stream()
                .<List<Object>>map(appSpecific -> Arrays.asList(
                        appSpecific.getNomeClasseTeste(),
                        appSpecific.getDuracaoExecucao()))
                .forEach(execucaoPorClasse::add);

        return execucaoPorClasse;
    }
}

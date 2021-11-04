package com.bandtec.br.finoban.infraestrutura.adapters;

import com.bandtec.br.finoban.dominio.enums.TestStatusGeralEnum;
import com.bandtec.br.finoban.dominio.resposta.TestReportDTO;
import com.bandtec.br.finoban.dominio.resposta.TestsDashboardDTO;
import com.bandtec.br.finoban.infraestrutura.helpers.NumberHelper;

import java.util.List;
import java.util.Objects;

public class TestsDashboardAdapter {

    private final List<TestReportDTO> testes;

    public TestsDashboardAdapter(List<TestReportDTO> testes) {
        this.testes = testes;
    }

    public TestsDashboardDTO testsToDashboardDTO()
    {
        TestsDashboardDTO dashboardDTO = new TestsDashboardDTO();
        dashboardDTO.setPorcentagemSucessoPorFuncoes(this.testsToPorcentagemSucessoPorFuncoes());
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

    public Object testsToPorcentagemSucessoPorFuncoes()
    {
        return null;
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

    public Object testsToPorcentagemSucessoPorDominio()
    {
        return null;
    }

    public Object testsToTempoMedioExecucaoPorDominio()
    {
        return null;
    }

    public Object testsToTempoMedioExecucaoPorClasse()
    {
        return null;
    }
}

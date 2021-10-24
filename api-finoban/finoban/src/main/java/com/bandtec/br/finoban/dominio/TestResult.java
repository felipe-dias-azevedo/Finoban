package com.bandtec.br.finoban.dominio;

import com.bandtec.br.finoban.dominio.DAO.TestResultReportDAO;

public class TestResult {

    private String classe;
    private String nomeTeste;
    private String status;
    private String estagio;
    private Long duracao;

    public TestResult(TestResultReportDAO testResultReport) {
        this.nomeTeste = testResultReport.getName();
        this.status = testResultReport.getStatus();
        this.estagio = testResultReport.getStage();
        this.duracao = this.tratarDuracao(testResultReport);
        this.classe = this.tratarClasse(testResultReport.getFullName());
    }

    private String tratarClasse(String nomeClasse) {
        return nomeClasse.replace("com.bandtec.br.finoban.", "");
    }

    private Long tratarDuracao(TestResultReportDAO testResultReport) {
        return testResultReport.getStop() - testResultReport.getStart();
    }


    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getNomeTeste() {
        return nomeTeste;
    }

    public void setNomeTeste(String nomeTeste) {
        this.nomeTeste = nomeTeste;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEstagio() {
        return estagio;
    }

    public void setEstagio(String estagio) {
        this.estagio = estagio;
    }

    public Long getDuracao() {
        return duracao;
    }

    public void setDuracao(Long duracao) {
        this.duracao = duracao;
    }
}

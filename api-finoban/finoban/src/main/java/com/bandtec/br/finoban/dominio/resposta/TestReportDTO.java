package com.bandtec.br.finoban.dominio.resposta;

import com.bandtec.br.finoban.dominio.DAO.TestReportDAO;
import lombok.Getter;
import lombok.Setter;

public class TestReportDTO {

    @Getter
    @Setter
    private String nomeClasse;

    @Getter
    @Setter
    private String nomeTeste;

    @Getter
    @Setter
    private String status;

    @Getter
    @Setter
    private String estagio;

    @Getter
    @Setter
    private String duracao;

    public TestReportDTO(TestReportDAO testReports) {
        this.nomeClasse = testReports.getNomeClasse();
        this.nomeTeste = testReports.getNomeTeste();
        this.status = testReports.getStatus();
        this.estagio = testReports.getEstagio();
        this.duracao = testReports.getDuracao();
    }
}

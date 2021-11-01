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
    private Integer duracao;

    @Getter
    @Setter
    private String dataInsercao;

}

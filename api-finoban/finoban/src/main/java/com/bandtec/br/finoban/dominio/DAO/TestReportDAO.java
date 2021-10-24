package com.bandtec.br.finoban.dominio.DAO;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

public class TestReportDAO {

    @Getter
    @Setter
    @CsvBindByName(column = "classe", required = true)
    private String nomeClasse;

    @Getter
    @Setter
    @CsvBindByName(required = true)
    private String nomeTeste;

    @Getter
    @Setter
    @CsvBindByName(required = true)
    private String status;

    @Getter
    @Setter
    @CsvBindByName(required = true)
    private String estagio;

    @Getter
    @Setter
    @CsvBindByName(required = true)
    private String duracao;

}

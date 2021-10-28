package com.bandtec.br.finoban.dominio.DAO;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

public class TestReportDAO {

    @Getter
    @Setter
    @CsvBindByName(column = "CLASSE", required = true)
    private String nomeClasse;

    @Getter
    @Setter
    @CsvBindByName(column = "NOMETESTE", required = true)
    private String nomeTeste;

    @Getter
    @Setter
    @CsvBindByName(column = "STATUS", required = true)
    private String status;

    @Getter
    @Setter
    @CsvBindByName(column = "ESTAGIO", required = true)
    private String estagio;

    @Getter
    @Setter
    @CsvBindByName(column = "DURACAO", required = true)
    private String duracao;

    @Getter
    @Setter
    @CsvBindByName(column = "DATAINSERCAO", required = true)
    private String dataInsercao;

}

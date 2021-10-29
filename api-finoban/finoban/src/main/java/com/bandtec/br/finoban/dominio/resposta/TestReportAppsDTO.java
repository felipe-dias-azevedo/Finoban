package com.bandtec.br.finoban.dominio.resposta;

import com.bandtec.br.finoban.dominio.enums.TestStatusGeralEnum;
import lombok.Getter;
import lombok.Setter;

public class TestReportAppsDTO {

    @Getter
    @Setter
    private String nomeAplicacao;

    @Getter
    @Setter
    private TestStatusGeralEnum statusGeral;

    @Getter
    @Setter
    private String dataExecucao;

    @Getter
    @Setter
    private Double porcentagemSucesso;

    @Getter
    @Setter
    private Double duracaoExecucao;
}

package com.bandtec.br.finoban.dominio.resposta;

import lombok.Getter;
import lombok.Setter;

public class TestReportAppSpecificDTO {

    @Getter
    @Setter
    private String nomeClasseTeste;

    @Getter
    @Setter
    private Double porcentagemCobertura;

    @Getter
    @Setter
    private Integer quantidadeFuncoes;

    @Getter
    @Setter
    private Double duracaoExecucao;
}

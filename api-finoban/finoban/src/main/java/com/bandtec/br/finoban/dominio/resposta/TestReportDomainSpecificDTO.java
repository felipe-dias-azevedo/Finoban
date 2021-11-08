package com.bandtec.br.finoban.dominio.resposta;

import lombok.Getter;
import lombok.Setter;

public class TestReportDomainSpecificDTO {

    @Getter
    @Setter
    private String nomeDominioTeste;

    @Getter
    @Setter
    private Double porcentagemSucesso;

    @Getter
    @Setter
    private Integer quantidadeFuncoes;

    @Getter
    @Setter
    private Double duracaoExecucao;

}

package com.bandtec.br.finoban.dominio.resposta;

import com.bandtec.br.finoban.dominio.enums.TestStatusGeralEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class TestsDashboardDTO {
    
    /** Porcentagem geral de sucesso */
    @Getter
    @Setter
    private Double porcentagemGeralSucesso;
    
    /** Quantidade de testes executados */
    @Getter
    @Setter
    private Integer quantidadeTestes;
    
    /** Data de execução do último teste */
    @Getter
    @Setter
    private String ultimaDataExecucao;
    
    /** Duração da execução total dos testes */
    @Getter
    @Setter
    private Double duracaoExecucao;
    
    /** Status geral dos testes (Passou[verde] ou Falhou[vermelho]) */
    @Getter
    @Setter
    private TestStatusGeralEnum statusGeralTestes;
    
    /** Porcentagem de sucesso por dominio (ex: controller, service, etc) */
    @Getter
    @Setter
    private List<List<Object>> porcentagemSucessoPorDominio;
    
    /** Tempo médio de execução por domínio */
    @Getter
    @Setter
    private List<List<Object>> tempoMedioExecucaoPorDominio;

    /** Porcentagem de sucesso por quantidade de funções */
    @Getter
    @Setter
    private List<List<Object>> porcentagemSucessoPorClasse;
    
    /** Tempo médio de execução por classe */
    @Getter
    @Setter
    private List<List<Object>> tempoMedioExecucaoPorClasse;

}

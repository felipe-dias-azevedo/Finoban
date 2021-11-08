import graphicEnum from './graphicEnum';

const chartsEnum = {
    RENDIMENTO_MENSAL: {
        id: 0,
        graphicId: graphicEnum.LINHA,
        name: "Renda Média dos Usuários deste Mês"
    },
    PORCENTUAL_PERDAS: {
        id: 1,
        graphicId: graphicEnum.GAUGE,
        name: "Nível de Desistência dos Usuários"
    },
    PROJECAO_RENDIMENTO: {
        id: 2,
        graphicId: graphicEnum.PROJECAO,
        name: "Projeção Rendimento Próximo Mês"
    },
    TEMPO_PERMANENCIA: {
        id: 3,
        graphicId: graphicEnum.HISTOGRAMA,
        name: "Tempo de Permanência"
    },
    AVALIACAO_SITE: {
        id: 4,
        graphicId: graphicEnum.HISTOGRAMA,
        name: "Avaliações do Site"
    },
    REGIAO_RENDA: {
        id: 5,
        graphicId: graphicEnum.DISPERSAO,
        name: "Regiões X Renda"
    },
    VALOR_IMOVEL_IDADE: {
        id: 6,
        graphicId: graphicEnum.DISPERSAO,
        name: "Valor Imóvel X Idade"
    },
    REGIOES_ESCOLHIDAS: {
        id: 7,
        graphicId: graphicEnum.HISTOGRAMA,
        name: "Bairros Escolhidos"
    },
    VALOR_IMOVEL_RENDA: {
        id: 8,
        graphicId: graphicEnum.DISPERSAO,
        name: "Valor Imóvel X Renda"
    },
    CEP_REGIAO_ESCOLHIDA: {
        id: 9,
        graphicId: graphicEnum.MAPA,
        name: "Bairro Usuário X Bairro Escolhido"
    },
    BANCOS_ESCOLHIDOS: {
        id: 10,
        graphicId: graphicEnum.HISTOGRAMA,
        name: "Bancos Escolhidos"
    },
    SUCESSO_GERAL: {
        id: 11,
        graphicId: graphicEnum.VALOR,
        name: "Porcentagem Geral de Sucesso"
    },
    QUANTIDADE_TESTES: {
        id: 12,
        graphicId: graphicEnum.VALOR,
        name: "Quantidade de Testes Executados"
    },
    DATA_EXECUCAO: {
        id: 13,
        graphicId: graphicEnum.VALOR,
        name: "Data da Execução"
    },
    DURACAO_EXECUCAO: {
        id: 14,
        graphicId: graphicEnum.VALOR,
        name: "Duração da Execução"
    },
    STATUS_GERAL: {
        id: 15,
        graphicId: graphicEnum.STATUS,
        name: "Status Geral dos Testes"
    },
    SUCESSO_DOMINIO: {
        id: 16,
        graphicId: graphicEnum.AREA,
        name: "Sucesso por Domínio"
    },
    TEMPO_MEDIO_DOMINIO: {
        id: 17,
        graphicId: graphicEnum.AREA,
        name: "Tempo médio por Domínio"
    },
    SUCESSO_CLASSE: {
        id: 18,
        graphicId: graphicEnum.AREA,
        name: "Sucesso por Classe"
    },
    TEMPO_MEDIO_CLASSE: {
        id: 19,
        graphicId: graphicEnum.AREA,
        name: "Tempo médio por Classe"
    }
}

export default chartsEnum;
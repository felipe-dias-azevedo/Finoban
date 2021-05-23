import graphicEnum from './graphicEnum';

const chartsEnum = {
    RENDIMENTO_MENSAL: {
        id: 0,
        graphicId: graphicEnum.LINHA,
        name: "Rendimento Mensal"
    },
    PORCENTUAL_PERDAS: {
        id: 1,
        graphicId: graphicEnum.GAUGE,
        name: "Porcentual de Perdas de Clientes"
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
        name: "Avaliações Site"
    },
    REGIAO_RENDA: {
        id: 5,
        graphicId: graphicEnum.DISPERSAO,
        name: "Regiões por Renda"
    },
    VALOR_IMOVEL_IDADE: {
        id: 6,
        graphicId: graphicEnum.DISPERSAO,
        name: "Valor Imóvel por Idade"
    },
    REGIOES_ESCOLHIDAS: {
        id: 7,
        graphicId: graphicEnum.HISTOGRAMA,
        name: "Regiões Escolhidas"
    },
    VALOR_IMOVEL_RENDA: {
        id: 8,
        graphicId: graphicEnum.DISPERSAO,
        name: "Valor Imóvel por Renda"
    },
    CEP_REGIAO_ESCOLHIDA: {
        id: 9,
        graphicId: graphicEnum.MAPA,
        name: "Cep Usuário por Região Escolhida"
    },
    BANCOS_ESCOLHIDOS: {
        id: 10,
        graphicId: graphicEnum.HISTOGRAMA,
        name: "Bancos Escolhidos"
    },
}

export default chartsEnum;
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
}

export default chartsEnum;
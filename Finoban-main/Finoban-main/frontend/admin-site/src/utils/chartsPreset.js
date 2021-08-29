import chartsEnum from './chartsEnum';

const chartsPreset = [
    {
        id: chartsEnum.RENDIMENTO_MENSAL.id, 
        graphic: chartsEnum.RENDIMENTO_MENSAL.graphicId, 
        name: chartsEnum.RENDIMENTO_MENSAL.name
    },
    {
        id: chartsEnum.PORCENTUAL_PERDAS.id, 
        graphic: chartsEnum.PORCENTUAL_PERDAS.graphicId, 
        name: chartsEnum.PORCENTUAL_PERDAS.name
    },
    {
        id: chartsEnum.PROJECAO_RENDIMENTO.id, 
        graphic: chartsEnum.PROJECAO_RENDIMENTO.graphicId, 
        name: chartsEnum.PROJECAO_RENDIMENTO.name
    },
    {
        id: chartsEnum.TEMPO_PERMANENCIA.id, 
        graphic: chartsEnum.TEMPO_PERMANENCIA.graphicId, 
        name: chartsEnum.TEMPO_PERMANENCIA.name
    },
    {
        id: chartsEnum.AVALIACAO_SITE.id, 
        graphic: chartsEnum.AVALIACAO_SITE.graphicId, 
        name: chartsEnum.AVALIACAO_SITE.name
    },
    {
        id: chartsEnum.REGIAO_RENDA.id, 
        graphic: chartsEnum.REGIAO_RENDA.graphicId, 
        name: chartsEnum.REGIAO_RENDA.name
    },
    {
        id: chartsEnum.VALOR_IMOVEL_IDADE.id, 
        graphic: chartsEnum.VALOR_IMOVEL_IDADE.graphicId, 
        name: chartsEnum.VALOR_IMOVEL_IDADE.name
    },
    {
        id: chartsEnum.REGIOES_ESCOLHIDAS.id, 
        graphic: chartsEnum.REGIOES_ESCOLHIDAS.graphicId, 
        name: chartsEnum.REGIOES_ESCOLHIDAS.name
    },
    {
        id: chartsEnum.VALOR_IMOVEL_RENDA.id, 
        graphic: chartsEnum.VALOR_IMOVEL_RENDA.graphicId, 
        name: chartsEnum.VALOR_IMOVEL_RENDA.name
    },
    {
        id: chartsEnum.CEP_REGIAO_ESCOLHIDA.id, 
        graphic: chartsEnum.CEP_REGIAO_ESCOLHIDA.graphicId, 
        name: chartsEnum.CEP_REGIAO_ESCOLHIDA.name
    },
    {
        id: chartsEnum.BANCOS_ESCOLHIDOS.id, 
        graphic: chartsEnum.BANCOS_ESCOLHIDOS.graphicId, 
        name: chartsEnum.BANCOS_ESCOLHIDOS.name
    },
];

export default chartsPreset
import graphicEnum from '../utils/graphicEnum';
import chartsEnum from './chartsEnum';

function DataChartHandler ( chartEnum, chartId, data ) {

    switch (chartEnum) {
        case graphicEnum.LINHA:
            return data.rendimentoMensal;

        case graphicEnum.GAUGE:
            return data.porcentualPerdas;

        case graphicEnum.PROJECAO:
            return data.projecaoRendimento;

        case graphicEnum.HISTOGRAMA:
            switch (chartId) {
                case chartsEnum.TEMPO_PERMANENCIA.id:
                    data.tempoPermanencia[data.tempoPermanencia.length - 1][0] += "+";
                    return data.tempoPermanencia;

                case chartsEnum.AVALIACAO_SITE.id:
                    return data.avaliacaoSite;

                case chartsEnum.REGIOES_ESCOLHIDAS.id:
                    return data.regioesEscolhidas;

                case chartsEnum.BANCOS_ESCOLHIDOS.id:
                    return data.bancosEscolhidos;
                default: 
                    return null;
            }
        case graphicEnum.DISPERSAO:
            switch ( chartId ) {
                case chartsEnum.REGIAO_RENDA.id:
                    return data.regiaoRenda;

                case chartsEnum.VALOR_IMOVEL_IDADE.id:
                    return data.valorImovelIdade;

                case chartsEnum.VALOR_IMOVEL_RENDA.id:
                    return data.valorImovelRenda;

                default:
                    return null;
            }
        case graphicEnum.MAPA:
            return data.cepRegiaoEscolhida;
            
        default:
            return null;
    }
}

export default DataChartHandler;
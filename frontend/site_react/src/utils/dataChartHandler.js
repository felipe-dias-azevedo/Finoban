import graphicEnum from '../utils/graphicEnum';
import chartsEnum from './chartsEnum';
import dateHelper from './dateHelper';

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
                    // if (!!data.tempoPermanencia) {
                    //     data.tempoPermanencia[data.tempoPermanencia.length - 1][0] += "+";
                    // }
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
        
        case graphicEnum.AREA:
            switch ( chartId ) {
                case chartsEnum.SUCESSO_DOMINIO.id:
                    return data.porcentagemSucessoPorDominio;

                case chartsEnum.TEMPO_MEDIO_DOMINIO.id:
                    return data.tempoMedioExecucaoPorDominio;

                case chartsEnum.SUCESSO_CLASSE.id:
                    return data.porcentagemSucessoPorClasse;

                case chartsEnum.TEMPO_MEDIO_CLASSE.id:
                    return data.tempoMedioExecucaoPorClasse;
                
                default:
                    return null;
            }
        case graphicEnum.VALOR:
            switch ( chartId ) {
                case chartsEnum.SUCESSO_GERAL.id:
                    return (data.porcentagemGeralSucesso * 100) + ' %';

                case chartsEnum.QUANTIDADE_TESTES.id:
                    return data.quantidadeTestes;

                case chartsEnum.DURACAO_EXECUCAO.id:
                    return data.duracaoExecucao + ' s';

                case chartsEnum.DATA_EXECUCAO.id:
                    return dateHelper(data.ultimaDataExecucao);
                    //return data.ultimaDataExecucao;

                default:
                    return null;
            }
        case graphicEnum.STATUS:
            return data.statusGeralTestes;

        default:
            return null;
    }
}

export default DataChartHandler;
import graphicEnum from '../utils/graphicEnum';
import chartsEnum from './chartsEnum';

function DataChartHandler ( chartEnum, chartId, data ) {
    
    const rendimentoData = data.rendimentoMensal;
    const porcentualPerdasData = data.porcentualPerdas;
    const projecaoRendimentoData = data.projecaoRendimento;
    const tempoPermanenciaData = data.tempoPermanencia;
    const avaliacaoSiteData = data.avaliacaoSite;
    const regioesEscolhidasData = data.regioesEscolhidas;
    const bancosEscolhidosData = data.bancosEscolhidos;
    const regiaoRendaData = data.regiaoRenda;
    const imovelIdadeData = data.valorImovelIdade;
    const imovelRendaData = data.valorImovelRenda;
    const cepRegiaoEscolhidaData = data.cepRegiaoEscolhida;

    switch (chartEnum) {
        case graphicEnum.LINHA:
            // let dadoAtualLinha;
            // let rendimento = [];
            // rendimento.push(['data', 'valor']);
            // for (let i = 0; i < rendimentoData.length; i++) {
            //     dadoAtualLinha = rendimentoData[i];
            //     rendimento.push([dadoAtualLinha.data, dadoAtualLinha.valor]);
            // }
            // return rendimento;
            return rendimentoData;

        case graphicEnum.GAUGE:
            // const porcentualPerdas = [
            //     ['Label', 'Value'],
            //     ['DesistĂȘncia', porcentualPerdasData]
            // ];
            // return porcentualPerdas;
            return porcentualPerdasData;

        case graphicEnum.PROJECAO:
            // let dadoAtualProjecao;
            // let projecao = [];
            // projecao.push(['data', 'valor']);
            // for (let i = 0; i < projecaoRendimentoData.length; i++) {
            //     dadoAtualProjecao = projecaoRendimentoData[i];
            //     projecao.push([dadoAtualProjecao.data, dadoAtualProjecao.valor]);
            // }
            // return projecao;
            return projecaoRendimentoData;

        case graphicEnum.HISTOGRAMA:
            switch (chartId) {
                case chartsEnum.TEMPO_PERMANENCIA.id:
                    // let dadoAtual;
                    // let tempoPermanencia = [];
                    // tempoPermanencia.push(['Tempo de PermanĂȘncia', 'Quantidade']);
                    // for (let i = 0; i < tempoPermanenciaData.length - 1; i++) {
                    //     dadoAtual = tempoPermanenciaData[i];
                    //     tempoPermanencia.push([dadoAtual.permanencia, dadoAtual.quantidade]);    
                    // }
                    // dadoAtual = tempoPermanenciaData[tempoPermanenciaData.length-1];
                    // tempoPermanencia.push([`${dadoAtual.permanencia}+`, dadoAtual.quantidade]);
                    // return tempoPermanencia;
                    tempoPermanenciaData[tempoPermanenciaData.length - 1][0] += "+";
                    return tempoPermanenciaData;

                case chartsEnum.AVALIACAO_SITE.id:
                    // let avaliacaoSite = [];
                    // avaliacaoSite.push(['Tipo de AvaliaĂ§ĂŁo', 'Quantidade']);
                    // avaliacaoSite.push(['Gostou', avaliacaoSiteData.gostou]);
                    // avaliacaoSite.push(['NĂŁo Gostou', avaliacaoSiteData.naoGostou]);
                    // avaliacaoSite.push(['Sem Feedback', avaliacaoSiteData.semFeedback]);
                    // return avaliacaoSite;
                    return avaliacaoSiteData;

                case chartsEnum.REGIOES_ESCOLHIDAS.id:
                    // let regioesEscolhidas = [];
                    // regioesEscolhidas.push(['RegiĂŁo Escolhida', 'Quantidade']);
                    // regioesEscolhidas.push(['Brooklin', regioesEscolhidasData.brooklin]);
                    // regioesEscolhidas.push(['Centro', regioesEscolhidasData.centro]);
                    // regioesEscolhidas.push(['ConsolaĂ§ĂŁo', regioesEscolhidasData.consolacao]);
                    // regioesEscolhidas.push(['Interlagos', regioesEscolhidasData.interlagos]);
                    // regioesEscolhidas.push(['Mooca', regioesEscolhidasData.mooca]);
                    // regioesEscolhidas.push(['Santo Amaro', regioesEscolhidasData.santoAmaro]);
                    // return regioesEscolhidas;
                    return regioesEscolhidasData;

                case chartsEnum.BANCOS_ESCOLHIDOS.id:
                    // let bancosEscolhidos = [];
                    // bancosEscolhidos.push(['Banco Escolhido', 'Quantidade']);
                    // bancosEscolhidos.push(['Banco Cifra', bancosEscolhidosData.cifra]);
                    // bancosEscolhidos.push(['Banco do Presil', bancosEscolhidosData.presil]);
                    // bancosEscolhidos.push(['S16 Bank', bancosEscolhidosData.s16]);
                    // return bancosEscolhidos;
                    return bancosEscolhidosData;
                default: 
                    return null;
            }
        case graphicEnum.DISPERSAO:
            switch ( chartId ) {
                case chartsEnum.REGIAO_RENDA.id:
                    // let dadoAtualRR;
                    // let regiaoRenda = [];
                    // regiaoRenda.push(['Valor RegiĂŁo', 'Valor Renda']);
                    // for (let i = 0; i < regiaoRendaData.length; i++) {
                    //     dadoAtualRR = regiaoRendaData[i];
                    //     regiaoRenda.push([dadoAtualRR.valorRegiao, dadoAtualRR.renda]);
                    // }
                    // return regiaoRenda;
                    return regiaoRendaData;

                case chartsEnum.VALOR_IMOVEL_IDADE.id:
                    // let dadoAtualII;
                    // let imovelIdade = [];
                    // imovelIdade.push(['Valor ImĂłvel', 'Idade']);
                    // for (let i = 0; i < imovelIdadeData.length; i++) {
                    //     dadoAtualII = imovelIdadeData[i];
                    //     imovelIdade.push([dadoAtualII.valorImovel, dadoAtualII.idade]);
                    // }
                    // return imovelIdade;
                    return imovelIdadeData;

                case chartsEnum.VALOR_IMOVEL_RENDA.id:
                    // let dadoAtualIR;
                    // let imovelRenda = [];
                    // imovelRenda.push(['Valor ImĂłvel', 'Valor Renda']);
                    // for (let i = 0; i < imovelRendaData.length; i++) {
                    //     dadoAtualIR = imovelRendaData[i];
                    //     imovelRenda.push([dadoAtualIR.valorImovel, dadoAtualIR.renda]);
                    // }
                    // return imovelRenda;
                    return imovelRendaData;

                default:
                    return null;
            }
        case graphicEnum.MAPA:
            // let cepRegiao = [];
            // cepRegiao.push([
            //     'Bairro',
            //     'Centro',
            //     'ConsolaĂ§ĂŁo',
            //     'Brooklin',
            //     'Mooca',
            //     'Santo Amaro',
            //     'Interlagos'
            // ]);

            // let regioesLidas = [];
            // let regiaoEmRegioesLidas;
            // let regiaoAtual;

            // for (let i = 0; i < cepRegiaoEscolhidaData.length; i++) {
            //     regiaoAtual = cepRegiaoEscolhidaData[i].bairroCliente;
            //     regiaoEmRegioesLidas = false;
            //     for (let j = 0; j < regioesLidas.length; j++) {
            //         if (regioesLidas[j] === regiaoAtual) {
            //             regiaoEmRegioesLidas = true;
            //         }
            //     }
            //     if (!regiaoEmRegioesLidas) {
            //         cepRegiao.push([regiaoAtual,0,0,0,0,0,0]);
            //         for (let h = 0; h < cepRegiaoEscolhidaData.length; h++) {
            //             if (regiaoAtual === cepRegiaoEscolhidaData[h].bairroCliente) {
            //                 for (let k = 0; k < cepRegiao[0].length-1; k++) {
            //                     if (cepRegiaoEscolhidaData[h].regiaoEscolhida === cepRegiao[0][k+1]) {
            //                         cepRegiao[cepRegiao.length-1][k+1] = cepRegiaoEscolhidaData[h].contagem;
            //                     }
            //                 }
            //             }
            //         }
            //         regioesLidas.push(regiaoAtual);
            //     }
            // }
            // console.log(cepRegiao);
            // console.table(cepRegiao);
            // return cepRegiao;
            return cepRegiaoEscolhidaData;
            
        default:
            return null;
    }
}

export default DataChartHandler;
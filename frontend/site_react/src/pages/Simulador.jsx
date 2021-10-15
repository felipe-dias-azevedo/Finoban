import React from 'react';
import Header from '../components/Header';
import BankCard from '../components/BankCard';
import Footer from '../components/Footer';

function Simulador() {

    // Local Storage
    var dadosSimuladorRecebido = sessionStorage.getItem("dadosSimulador");
    var respostaSimulacao = JSON.parse(sessionStorage.getItem("respostaFinanciamento"));
    let dataSimulacao = JSON.parse(dadosSimuladorRecebido);
    console.log(dataSimulacao);
    if (dadosSimuladorRecebido == null) {
        window.location.href="/";
    }

    // Taxas
    var taxa1 = respostaSimulacao.data[0].data.taxaTotal;
    var taxa2 = respostaSimulacao.data[1].data.taxaTotal;
    var taxa3 = respostaSimulacao.data[2].data.taxaTotal;

    let financiamentoPresil = financiar(dataSimulacao.valorImovel, taxa1, dataSimulacao.tempoFinanciamento);
    let financiamentoS16 = financiar(dataSimulacao.valorImovel, taxa2, dataSimulacao.tempoFinanciamento);
    let financiamentoCifra = financiar(dataSimulacao.valorImovel, taxa3, dataSimulacao.tempoFinanciamento);

    function financiar(valor_imovel, valor_taxa_juros, anos_a_serem_pagos) {
        var imovel = valor_imovel;
        var taxa_juros = valor_taxa_juros;
        var valor_a_pagar = 0;
        var anos_pagos = 0;
        var prestacoes = [];
        var prestacao_sem_taxa;
        var prestacao_com_taxa;
        var primeira_prestacao;
    
        while (anos_pagos < anos_a_serem_pagos) {
            anos_pagos++;
            prestacao_sem_taxa = imovel / anos_a_serem_pagos;
            prestacao_com_taxa = imovel * taxa_juros;
            if (anos_pagos == 1) {
                primeira_prestacao = (prestacao_com_taxa / 12) + (prestacao_sem_taxa / 12);
            }
            imovel = imovel - (prestacao_sem_taxa);
            valor_a_pagar += (prestacao_sem_taxa + prestacao_com_taxa);
            prestacoes.push(prestacao_sem_taxa + prestacao_com_taxa);
            
        }
        const data_financiamento = {valor_a_pagar:valor_a_pagar, primeira_prestacao: primeira_prestacao, prestacoes:prestacoes, imovel:imovel}

        return data_financiamento;
    }

    var primeiraPrestacaoPresil = financiamentoPresil.prestacoes[0] / 12;
    var primeiraPrestacaoS16 = financiamentoS16.prestacoes[0] / 12;
    var valorPrimeiraPrestacaoCifra = financiamentoCifra.prestacoes[0] / 12;

    var objSetLocalStorages = {
        tempoFinanciamento: dataSimulacao.tempoFinanciamento,
        porcentagemRenda: dataSimulacao.porcentagemRenda,
        listaFinanciamentoCifra: financiamentoCifra.prestacoes,
        valorImovel: dataSimulacao.valorImovel,
        valorImovelFormatado: parseInt(dataSimulacao.valorImovel).toLocaleString('pt-br',{style: 'currency', currency: 'BRL'}),
        valorImovelPresil: financiamentoPresil.valor_a_pagar,
        valorImovelS16: financiamentoS16.valor_a_pagar,
        valorImovelCifra: financiamentoCifra.valor_a_pagar,
        valorPrimeiraPrestacaoPresil: primeiraPrestacaoPresil.toLocaleString('pt-br',{style: 'currency', currency: 'BRL'}),
        valorPrimeiraPrestacaoS16: primeiraPrestacaoS16.toLocaleString('pt-br',{style: 'currency', currency: 'BRL'}),
        valorPrimeiraPrestacaoCifra: valorPrimeiraPrestacaoCifra.toLocaleString('pt-br',{style: 'currency', currency: 'BRL'}),
        valorImovelS16Formatado: financiamentoS16.valor_a_pagar.toLocaleString('pt-br',{style: 'currency', currency: 'BRL'}),
        valorImovelPresilFormatado: financiamentoPresil.valor_a_pagar.toLocaleString('pt-br',{style: 'currency', currency: 'BRL'}),
        valorImovelCifraFormatado: financiamentoCifra.valor_a_pagar.toLocaleString('pt-br',{style: 'currency', currency: 'BRL'}),
        taxa1:  taxa1.toFixed(2),
        taxa2: taxa2.toFixed(2),
        taxa3: taxa3.toFixed(2)
    }
    
    console.log(objSetLocalStorages);

    // Objeto enviado para a dashboard
    var objParaDashboard = sessionStorage.setItem("objDashboard", JSON.stringify(objSetLocalStorages));
    
    return (
        <>
            <Header />
            
            <div className="center">
                <div className="box box-titulo center">
                    <h1>Simulador</h1>
                </div>
                <div className="box box-subtitulo center">
                    <h4>Confira as taxas de cada banco referente aos dados mencionados</h4>
                </div>
                <div className="bancos">
                    <BankCard 
                        banco="Banco do Presil" 
                        taxa_t ={(taxa1).toFixed(2)} 
                        primeira_p={objSetLocalStorages.valorPrimeiraPrestacaoPresil} 
                        valor_f={objSetLocalStorages.valorImovelPresilFormatado}
                        valor_s={(((respostaSimulacao.data[0].data.dfi) + (respostaSimulacao.data[0].data.mip))*100).toFixed(2)
                        }     
                        
                    />

                    <BankCard 
                        banco="S16 Bank"
                        taxa_t ={(taxa2).toFixed(2)} 
                        primeira_p={objSetLocalStorages.valorPrimeiraPrestacaoS16} 
                        valor_f={objSetLocalStorages.valorImovelS16Formatado} 
                        valor_s={(((respostaSimulacao.data[1].data.dfi) + (respostaSimulacao.data[1].data.mip))*100).toFixed(2)} 
                         
                    />
                    <BankCard 
                        banco="Banco Cifra" 
                        taxa_t ={(taxa3.toFixed(2))} 
                        primeira_p={objSetLocalStorages.valorPrimeiraPrestacaoCifra}
                        valor_f={objSetLocalStorages.valorPrimeiraPrestacaoCifra} 
                        valor_s={(((respostaSimulacao.data[2].data.dfi) + (respostaSimulacao.data[2].data.mip)) / 100).toFixed(2)} 
                    />
                </div>
            </div>
            <Footer />
        </>
    );
}

export default Simulador;
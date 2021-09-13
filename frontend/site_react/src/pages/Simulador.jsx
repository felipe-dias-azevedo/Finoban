import React from 'react';
import Header from '../components/Header';
import BankCard from '../components/BankCard';
import Footer from '../components/Footer';

function Simulador() {

    // Local Storage
    var horarioEntrada = sessionStorage.getItem("horarioEntrada");
    var idUsuario = localStorage.getItem("idUsuario");
    var dadosSimuladorRecebido = localStorage.getItem("dadosSimulador");
    var respostaSimulacao = JSON.parse(localStorage.getItem("respostaFinanciamento"));
    var porcentagemRenda = localStorage.getItem("porcentagemRenda");

    // Data
    var data = new Date();
    var dia = String(data.getDate()).padStart(2, '0');
    var mes = String(data.getMonth() + 1).padStart(2, '0');
    var ano = data.getFullYear();

    var hora = data.getHours();
    var minutos = data.getMinutes();
    if (minutos < 10) {
        minutos = '0' + minutos;
    }
    var segundos = data.getUTCSeconds();
    if (segundos < 10) {
        segundos = '0' + segundos;
    }

    var milisegundos = data.getMilliseconds();

    var dataSaida = ano + '-' + mes + '-' + dia + 'T' + hora + ':' + minutos + ':' + segundos + '.' + milisegundos;

    let dataSimulacao = JSON.parse(dadosSimuladorRecebido);
    var valorImovelRecebido = dataSimulacao.valorImovel;
    var tempoFinanciamentoRecebido = dataSimulacao.tempoFinanciamento;

    var respostaSimulacao = JSON.parse(localStorage.getItem("respostaFinanciamento"));

    var tempoFinanciamento = tempoFinanciamentoRecebido * 12;

    // Taxas
    var taxa1 = (respostaSimulacao[0].data.taxaTotal * 10);
    var taxa2 = respostaSimulacao[1].data.taxaTotal * 100;
    var taxa3 = respostaSimulacao[2].data.taxaTotal;

    var taxaPresil = localStorage.setItem("taxaPresil", taxa1);
    var taxaS16 = localStorage.setItem("taxaS16", taxa2);
    var taxaCifra = localStorage.setItem("taxaCifra", taxa3);

    let financiamentoPresil = financiar(valorImovelRecebido, taxa1/29, tempoFinanciamentoRecebido);
    let financiamentoS16 = financiar(valorImovelRecebido, taxa2/30, tempoFinanciamentoRecebido);
    let financiamentoCifra = financiar(valorImovelRecebido, taxa3/25, tempoFinanciamentoRecebido);

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

    var primeiraPrestacaoPresilFormatado = primeiraPrestacaoPresil.toLocaleString('pt-br',{style: 'currency', currency: 'BRL'});
    var primeiraPrestacaoS16Formatado = primeiraPrestacaoS16.toLocaleString('pt-br',{style: 'currency', currency: 'BRL'});
    var primeiraPrestacaoCifraFormatado = valorPrimeiraPrestacaoCifra.toLocaleString('pt-br',{style: 'currency', currency: 'BRL'});

    var objSetLocalStorages = {
        tempoFinanciamento: tempoFinanciamentoRecebido,
        porcentagemRenda: porcentagemRenda,
        listaFinanciamentoCifra: financiamentoCifra.prestacoes,
        valorImovel: valorImovelRecebido,
        valorImovelFormatado: parseInt(valorImovelRecebido).toLocaleString('pt-br',{style: 'currency', currency: 'BRL'}),
        valorImovelPresil: financiamentoPresil.valor_a_pagar,
        valorImovelS16: financiamentoS16.valor_a_pagar,
        valorImovelCifra: financiamentoCifra.valor_a_pagar,
        valorPrimeiraPrestacaoPresil: primeiraPrestacaoPresilFormatado,
        valorPrimeiraPrestacaoS16: primeiraPrestacaoS16Formatado,
        valorPrimeiraPrestacaoCifra: primeiraPrestacaoCifraFormatado,
        valorImovelS16Formatado: financiamentoS16.valor_a_pagar.toLocaleString('pt-br',{style: 'currency', currency: 'BRL'}),
        valorImovelPresilFormatado: financiamentoPresil.valor_a_pagar.toLocaleString('pt-br',{style: 'currency', currency: 'BRL'}),
        valorImovelCifraFormatado: financiamentoCifra.valor_a_pagar.toLocaleString('pt-br',{style: 'currency', currency: 'BRL'}),
        taxa1:  taxa1.toFixed(2),
        taxa2: taxa2.toFixed(2),
        taxa3: taxa3.toFixed(2)
    }
    
    console.log(objSetLocalStorages);

    // Objeto enviado para a dashboard
    var objParaDashboard = localStorage.setItem("objDashboard", JSON.stringify(objSetLocalStorages));

    var valorS16Formatado = financiamentoS16.valor_a_pagar.toLocaleString('pt-br',{style: 'currency', currency: 'BRL'});
    var valorPresilFormatado = financiamentoPresil.valor_a_pagar.toLocaleString('pt-br',{style: 'currency', currency: 'BRL'});
    var valorCifraFormatado = financiamentoCifra.valor_a_pagar.toLocaleString('pt-br',{style: 'currency', currency: 'BRL'});

    var valorImovelCifraFormatado = localStorage.setItem("valorCifraFormatado", valorCifraFormatado);
    
    return (
        <>
            <Header />
            
            <div className="center">
                <div className="box box-titulo center">
                    <h1>Simulador</h1>
                </div>
                <div className="box box-subtitulo center">
                    <h4>Valores dos Bancos referente aos dados mencionados</h4>
                </div>
                <div className="bancos center">
                    <BankCard 
                        banco="Banco do Presil" 
                        taxa_t ={(taxa1).toFixed(2)} 
                        primeira_p={primeiraPrestacaoPresilFormatado} 
                        valor_f={valorPresilFormatado}
                        valor_s={(((respostaSimulacao[0].data.dfi) + (respostaSimulacao[0].data.mip))*100).toFixed(2)
                        }     
                        
                    />

                    <BankCard 
                        banco="S16 Bank"
                        taxa_t ={(taxa2).toFixed(2)} 
                        primeira_p={primeiraPrestacaoS16Formatado} 
                        valor_f={valorS16Formatado} 
                        valor_s={(((respostaSimulacao[1].data.dfi) + (respostaSimulacao[1].data.mip))*100).toFixed(2)} 
                         
                    />
                    <BankCard 
                        banco="Banco Cifra" 
                        taxa_t ={(taxa3.toFixed(2))} 
                        primeira_p={primeiraPrestacaoCifraFormatado}
                        valor_f={valorCifraFormatado} 
                        valor_s={(((respostaSimulacao[2].data.dfi) + (respostaSimulacao[2].data.mip)) / 100).toFixed(2)} 
                    />
                </div>
            </div>
            <Footer />
        </>
    );
}

export default Simulador;
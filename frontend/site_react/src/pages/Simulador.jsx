import React from 'react';
import Header from '../components/Header';
import BankCard from '../components/BankCard';
import Footer from '../components/Footer';

function Simulador() {

    var testeRecebido = localStorage.getItem("testeChave");
    let dataSimulacao = JSON.parse(testeRecebido);
    var valorImovelRecebido = parseFloat(dataSimulacao.valorImovel);
    var tempoFinanciamentoRecebido = parseInt(dataSimulacao.tempoFinanciamento);

    var respostaSimulacao = JSON.parse(localStorage.getItem("respostaFinanciamento"));
    console.log(respostaSimulacao[0].data);
    console.log(respostaSimulacao[0].data.taxa);
    console.log(respostaSimulacao[1].data);
    console.log(respostaSimulacao[2].data);

    var taxa1 = respostaSimulacao[0].data.taxa * tempoFinanciamentoRecebido * 12;
    var taxa2 = respostaSimulacao[1].data.taxa * tempoFinanciamentoRecebido * 12;
    var taxa3 = respostaSimulacao[2].data.taxa * tempoFinanciamentoRecebido * 12;
    
    return (
        <>
            <Header />
            <div className="center">
                <div className="box box-titulo center">
                    <h1>Simulador</h1>
                </div>
                <div className="box box-subtitulo center">
                    <h4>Valores dos Bancos Referente aos Dados Mencionados</h4>
                </div>
                <div className="bancos center">
                    <BankCard 
                        banco="Banco do Presil" 
                        taxa_t ={(respostaSimulacao[0].data.taxa)} 
                        primeira_p="3000,00" 
                        valor_f={(valorImovelRecebido + taxa1)}
                        valor_s={(((respostaSimulacao[0].data.dfi) + (respostaSimulacao[0].data.mip))*100).toFixed(2)} 
                    
                    />
                    <BankCard 
                        banco="S16 Bank"
                        taxa_t ={(respostaSimulacao[1].data.taxa)} 
                        primeira_p="2000,00" 
                        valor_f={valorImovelRecebido + taxa2} 
                        valor_s={(((respostaSimulacao[1].data.dfi) + (respostaSimulacao[1].data.mip))*100).toFixed(2)} 
                         
                    />
                    <BankCard 
                        banco="Banco Cifra" 
                        taxa_t ={(respostaSimulacao[2].data.taxa)} 
                        primeira_p="3000,00" 
                        valor_f={valorImovelRecebido + taxa3} 
                        valor_s={(((respostaSimulacao[2].data.dfi) + (respostaSimulacao[2].data.mip))*100).toFixed(2)} 
                    />
                </div>
            </div>
            <Footer />
        </>
    );
}

export default Simulador;
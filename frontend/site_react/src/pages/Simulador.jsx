import React from 'react';
import Header from '../components/HeaderSimulador';
import BankCard from '../components/BankCard';
import Footer from '../components/Footer';
import Api from '../services/api';

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

    var tempoFinanciamento = tempoFinanciamentoRecebido * 12;
    console.log(tempoFinanciamento);

    var taxa1 = (respostaSimulacao[0].data.taxa * tempoFinanciamento);
    var taxa2 = (respostaSimulacao[1].data.taxa * tempoFinanciamento);
    var taxa3 = (respostaSimulacao[2].data.taxa * tempoFinanciamento);

    var horarioEntrada = sessionStorage.getItem("horarioEntrada");
    var idUsuario = localStorage.getItem("idUsuario");
    
    var data = new Date();
    var dia = String(data.getDate()).padStart(2, '0');
    var mes = String(data.getMonth() + 1).padStart(2, '0');
    var ano = data.getFullYear();

    var hora = data.getHours();
    var minutos = data.getMinutes();
    if(minutos < 10) {
        minutos = '0' + minutos;
    }
    var segundos = data.getUTCSeconds();
    if(segundos < 10) {
        segundos = '0' + segundos;
    }

    var milisegundos = data.getMilliseconds();

    var dataSaida = ano + '-' + mes + '-' + dia + 'T' + hora + ':' + minutos + ':' + segundos + '.' + milisegundos;

    var porcentagemRenda = localStorage.getItem("porcentagemRenda");

    window.onbeforeunload = confirmExit;
    function confirmExit(){
      reqAcesso();
      return "You have attempted to leave this page.  If you have made any changes to the fields without clicking the Save button, your changes will be lost.  Are you sure you want to exit this page?";
    }

    var acesso = {
        dataHoraEntrada: horarioEntrada,
        dataHoraSaida: dataSaida,
        paginaSaida: 3,
        statusSaida: 0,
        renda: dataSimulacao.renda,
        valorImovel: dataSimulacao.valorImovel,
        tempoFinanciamento: dataSimulacao.tempoFinanciamento,
        porcentagemRenda: parseInt(porcentagemRenda),
        bancoEscolhido: 2,
        fkRegiao: {
            idRegiao: 2000
        },
        fkCliente: {
            id: parseInt(idUsuario)
        },
    }

    console.log(acesso);

    function reqAcesso() {
    Api.post('/acessos', acesso, {
    }).then(e => {
      console.log(e.data);
    }).catch(e => {
      console.error(e)
    });
}      
    
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
                        taxa_t ={(respostaSimulacao[0].data.taxa)} 
                        primeira_p="3000,00" 
                        valor_f={(valorImovelRecebido + taxa1)}
                        valor_s={(((respostaSimulacao[0].data.dfi) + (respostaSimulacao[0].data.mip))*100).toFixed(2)
                        }     
                        
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
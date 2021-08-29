import api from '../services/api';
import React, { useState } from 'react';
import { useHistory } from 'react-router';

function CadastroAcesso() {

    const history = useHistory();

    const [renda, setRenda] = useState(0);
    const [valorImovel, setValorImovel] = useState(0);
    const [tempoFinanciamento, setTempoFinanciamento] = useState(0);
    const [porcentagemRenda, setPorcentagemRenda] = useState(0);
    const [bancoEscolhido, setbancoEscolhido] = useState(0);
    const [dataHoraEntrada, setDataHoraEntrada] = useState("");
    const [dataHoraSaida, setDataHoraSaida] = useState("");
    const [statusSaida, setStatusSaida] = useState(0);
    const [paginaSaida, setPaginaSaida] = useState(0);
    const [fkRegiao, setFkRegiao] = useState(0);
    const [fkCliente, setFkCliente] = useState(0);


    function inserirAcesso() {

        if (renda.trim() === "" ||
            valorImovel.trim() === "" ||
            tempoFinanciamento.trim() === "" ||
            tempoFinanciamento.trim() === "" ||
            porcentagemRenda.trim() === "" ||
            bancoEscolhido.trim() === "" ||
            dataHoraEntrada.trim() === "" ||
            dataHoraSaida.trim() === "" ||
            statusSaida.trim() === "" ||
            paginaSaida.trim() === "" ||
            fkRegiao.trim() === "" ||
            fkCliente.trim() === "") {
            return;
        }

        const dataAcesso = {
            renda: Number(renda),
            valorImovel: Number(valorImovel),
            tempoFinanciamento: Number(tempoFinanciamento),
            porcentagemRenda: Number(porcentagemRenda),
            bancoEscolhido: Number(bancoEscolhido),
            dataHoraEntrada,
            dataHoraSaida,
            statusSaida: Number(statusSaida),
            paginaSaida: Number(paginaSaida),
            fkRegiao: {
                idRegiao: Number(fkRegiao),
            },
            fkCliente: {
                id: Number(fkCliente),
            }
        }

        dataHoraEntrada.replace('T', ' ');

        console.log(dataAcesso);
        api.post('/acessos', dataAcesso, {
            'Content-Type': 'application/json',
            'Access-Control-Allow-Origin': '*',
            'Access-Control-Allow-Headers': 'Origin, X-Requested-With, Content-Type, Accept'
        }).then(e => {
            console.log(e);
            if (e.status === 201) {
                console.log("Acesso cadastrado");
                history.push('/admin/acesso')
            } else {
                console.log("deu erro");
            }
        }).catch(erro => {
            console.log(erro);
        });
    }

    return (

        <div id="cadastro">

            <section className="title-site">

                <h1>Cadastro Acesso</h1>

            </section>

            <div className="form-holder">

                <div className="input-holder">
                    <h3>Renda:</h3>
                    <input type="text" onChange={(e) => setRenda(e.target.value)}/>
                </div>

                <div className="input-holder">

                    <h3>Valor Imóvel:</h3>
                    <input type="text" onChange={(e) => setValorImovel(e.target.value)}/>
                </div>

                <div className="input-holder">
                    <h3>Tempo Financiamento:</h3>
                    <input type="text" onChange={(e) => setTempoFinanciamento(e.target.value)}/>
                </div>

                <div className="input-holder">
                    <h3>Porcentagem Renda:</h3>
                    <input type="text" onChange={(e) => setPorcentagemRenda(e.target.value)}/>
                </div>

                <div className="input-holder">
                    <h3>Banco Escolhido:</h3>
                    <input type="text"  onChange={(e)=> setbancoEscolhido(e.target.value)} />
                </div>

                <div className="input-holder">
                    <h3>Data Hora Entrada:</h3>
                    <input type="datetime-local" onChange={(e) => setDataHoraEntrada(e.target.value)}/>
                </div>

                <div className="input-holder">
                    <h3>Data Hora Saida:</h3>
                    <input type="datetime-local"  onChange={(e) => setDataHoraSaida(e.target.value)}/>
                </div>

                <div className="input-holder">
                    <h3>Status Saida:</h3>
                    <input type="text" onChange={(e) => setStatusSaida(e.target.value)}/>
                </div>

                <div className="input-holder">
                    <h3>Pagina Saida:</h3>
                    <input type="text" onChange={(e) => setPaginaSaida(e.target.value)}/>
                </div>

                <div className="input-holder">
                    <h3>fkRegiao:</h3>
                    <input type="text" onChange={(e) => setFkRegiao(e.target.value)}/>
                </div>

                <div className="input-holder">
                    <h3>fkCliente:</h3>
                    <input type="text"  onChange={(e) => setFkCliente(e.target.value)} />
                </div>

                <div className="button-holder">
                    <button onClick={inserirAcesso}>
                        Cadastrar
                    </button>
                </div>
                
            </div>
        </div>
    );
}

export default CadastroAcesso;
import React, { useEffect, useState } from 'react';
import Footer from '../components/Footer';
import Header from '../components/Header';
import { IoChevronBack } from 'react-icons/io5';
import { IoIosArrowDropdown } from 'react-icons/io';
import { useHistory } from 'react-router';
import api from '../services/api';
import CpfCnpj from "@react-br-forms/cpf-cnpj-mask";

function PaginaInicial() {

    const history = useHistory();

    const [sabeValorImovel, setSabeValorImovel] = useState(null);
    const [respondeuBotao, setRespondeuBotao] = useState(false);

    const [cnpj, setCnpj] = useState("");
    const [mask, setMask] = useState("");
    const [renda, setRenda] = useState("");
    const [valorImovel, setValorImovel] = useState("");
    const [tempoFinanciamento, setTempoFinanciamento] = useState("");
    const [porcentagemRenda, setPorcentagemRenda] = useState("");
    const [imoveisList, setImoveisList] = useState([]);

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

    var dataAtual = ano + '-' + mes + '-' + dia + 'T' + hora + ':' + minutos + ':' + segundos + '.' + milisegundos;

    var horarioEntrada = sessionStorage.setItem("horarioEntrada", dataAtual);

    useEffect(() => {
        api.get('/regioes').then(e => {
            const imoveis = e.data;
            if (e.status === 200) {
                setImoveisList(imoveis);
            }
        }).catch(e => {
            console.error(e);
        })
    }, [])

    async function irParaSimulador() {

        if (
            cnpj.trim() === "" ||
            renda.trim() === "" ||
            valorImovel.trim() === "" ||
            tempoFinanciamento.trim() === ""
        ) { return; }

        const dataSimulador = {
            cnpj: "123",
            renda: parseInt(renda),
            valorImovel: parseFloat(valorImovel),
            tempoFinanciamento: parseInt(tempoFinanciamento)
        };

        console.log(dataSimulador);
        var porcentagemRecebida = localStorage.setItem("porcentagemRenda", porcentagemRenda);

        await api.post('/financiamento', dataSimulador, {
        }).then(e => {
            console.log(e.data);
            var respostaSimulacao = e.data;
            if(e.status == 200) {
            var respostaFinanciamento = localStorage.setItem("respostaFinanciamento", JSON.stringify(respostaSimulacao));
            history.push({
                pathname: '/simulador',
                state: { data: dataSimulador }
            });
        }
        }).catch(e => {
            console.error(e)
            alert("Ocorreu um erro!")
        });

        const dados = dataSimulador;

        var dadosSimulador = localStorage.setItem("testeChave", JSON.stringify(dados));
    }

    return (
        <>
            <Header />
            <div className="imagem">
                <div className="box-texto-simples">
                    <p>Dúvidas sobre qual a melhor opção de financiamento pra você?</p>
                </div>
                <div className="box-texto-destaque">
                    <h1>Com simples passos, você terá acesso as <span className="fw-600"> melhores opções </span> de financiamento! </h1>
                </div>
                <div className="comecar center">
                    <a id="bt-comecar" href="#simulacao">
                    <IoIosArrowDropdown size={46} className="ml-30"/>
                        <p> Começar</p>
                    </a>
                </div>
            </div>
            <div className="simulacao" id="simulacao">
                <div className="pt3">
                    <h3>Insira os dados abaixo</h3>
                </div>

                {
                    respondeuBotao ?
                        (<div id="teste">
                            <div className="imovel">
                                <div className="voltar">
                                    <button className="no-bt" onClick={(e) => setRespondeuBotao(false)}>
                                        <IoChevronBack size={46} />
                                        <span> Voltar </span>
                                    </button>
                                </div>
                                <section>
                                    <p>CNPJ</p>
                                    <CpfCnpj
                                        placeholder="00.000.000/0000-00"
                                        type="tel"
                                        value={cnpj}
                                        onChange={(e, type) => {
                                            setCnpj(e.target.value);
                                            setMask(type === "CNPJ");
                                        }}
                                    />
                                </section>
                                <section>
                                    <p>Renda</p>
                                    <input
                                        type="text"
                                        placeholder="ex: 5000,00"
                                        onChange={(e => setRenda(e.target.value))}
                                    />
                                </section>
                                <section>
                                    {
                                        sabeValorImovel ?
                                            <>
                                                <p id="mudar">Valor do imóvel</p>
                                                <input
                                                    id="valor"
                                                    type="text"
                                                    placeholder="ex: 600000,00"
                                                    onChange={(e => setValorImovel(e.target.value))}
                                                />
                                            </>
                                            :
                                            <>
                                                <p id="mudar">Região</p>
                                                <select
                                                    id="select-imoveis"
                                                    onChange={(e) => { setValorImovel(e.target.value) }}
                                                >
                                                    <option value="">-- Selecione --</option>

                                                    {imoveisList.map(imovel => {
                                                        return (
                                                            <option
                                                                key={imovel.idRegiao}
                                                                value={imovel.valorRegiao}
                                                            >
                                                                {imovel.descricaoRegiao}
                                                            </option>
                                                        )
                                                    })}
                                                </select>
                                            </>
                                    }
                                </section>
                                <section>
                                    <p>Tempo financiamento</p>
                                    <input t
                                        ype="text"
                                        placeholder="ex: 30"
                                        maxLength="2"
                                        onChange={(e => setTempoFinanciamento(e.target.value))}
                                    />
                                </section>
                                <section>
                                    <p>Porcentagem de Renda</p>
                                    <input type="text"
                                        placeholder="ex: 15"
                                        maxLength="3" 
                                        onChange={(e => setPorcentagemRenda(e.target.value))}
                                        />
                                </section>
                                <section>
                                </section>
                            </div>
                            <button className="bt-simular" onClick={irParaSimulador} required>
                                Simular
                            </button>
                        </div>)
                        :
                        (<div id="teste2">
                            <div className="pt4">
                                <div className="container">
                                    <h3>Já sabe o valor do imóvel?</h3>
                                    <div className="btn-valor-imovel">
                                        <button className="bt" onClick={(e) => {
                                            setSabeValorImovel(true);
                                            setRespondeuBotao(true)
                                        }} id="sim"> Sim</button>
                                        <button className="bt" onClick={(e) => {
                                            setSabeValorImovel(false);
                                            setRespondeuBotao(true)
                                        }} id="nao"> Não</button>
                                    </div>
                                </div>
                            </div>
                        </div>)
                }
            </div>
            <Footer />
        </>
    );
}

export default PaginaInicial;
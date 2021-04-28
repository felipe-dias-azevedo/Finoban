import React, { useEffect, useState } from 'react';
import Footer from '../components/Footer';
import Header from '../components/Header';
import { BiChevronDownCircle } from 'react-icons/bi';
import { IoChevronBack } from 'react-icons/io5';
import { useHistory } from 'react-router';
import api from '../services/api';

function PaginaInicial() {

    const history = useHistory();

    const [sabeValorImovel, setSabeValorImovel] = useState(null);
    const [respondeuBotao, setRespondeuBotao] = useState(false);

    const [cnpj, setCnpj] = useState("")
    const [renda, setRenda] = useState("")
    const [valorImovel, setValorImovel] = useState("")
    const [tempoFinanciamento, setTempoFinanciamento] = useState("")
    const [imoveisList, setImoveisList] = useState([]);

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

    function irParaSimulador() {
        
        if (
            cnpj.trim() === "" ||
            renda.trim() === "" ||
            valorImovel.trim() === "" ||
            tempoFinanciamento.trim() === ""
        ) { return; }

        const dataSimulador = {
            cnpj,
            renda,
            valorImovel,
            tempoFinanciamento
        };

        console.log(dataSimulador);

        history.push({
            pathname:'/login', 
            state: {data: dataSimulador}
        });
    }

    return (
        <>
            <Header />
            <div className="imagem">
                <div className="box-texto-simples">
                    <p>Dúvidas sobre qual a melhor opção de financiamento pra você?</p>
                </div>
                <div className="box-texto-destaque">
                    <h1>Com simples passos, você terá acesso as <span> melhores opções </span> de financiamento! </h1>
                </div>
                <div className="comecar center">
                    <a id="bt-comecar" href="#simulacao">
                        <BiChevronDownCircle size={64} />
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
                                    <input
                                        type="text"
                                        placeholder="00.000.000/0000-00"
                                        onChange={(e => setCnpj(e.target.value))}
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
                                                    placeholder="ex:600000,00"
                                                    onChange={(e => setValorImovel(e.target.value))}
                                                />
                                            </>
                                            :
                                            <>
                                                <p id="mudar">Região</p>
                                                <select
                                                    id="select-imoveis"
                                                    onChange={(e) => {setValorImovel(e.target.value)}}
                                                >
                                                    <option value="">--Selecione--</option>

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
                                    <p>Tempo do financiamento (anos)</p>
                                    <input t
                                        ype="text"
                                        placeholder="ex:30"
                                        onChange={(e => setTempoFinanciamento(e.target.value))}
                                    />
                                </section>
                                <section>
                                    <p>Porcentagem de Renda (max:20)</p>
                                    <input type="ex:15" placeholder="ex:15" />
                                </section>
                                <section>
                                </section>
                            </div>
                            <button className="bt-simular" onClick={irParaSimulador}>
                                Simular
                            </button>
                        </div>)
                        :
                        (<div id="teste2">
                            <div className="pt4">
                                <div className="container">
                                    <h3>Já sabe o valor do imóvel?</h3>
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
                        </div>)
                }
            </div>
            <Footer />
        </>
    );
}

export default PaginaInicial;
import React, { useState } from 'react';
import Footer from '../components/Footer';
import Header from '../components/Header';
import { BiChevronDownCircle } from 'react-icons/bi';
import { IoChevronBack } from 'react-icons/io5';

function PaginaInicial() {

    const [sabeValorImovel, setSabeValorImovel] = useState(null);
    const [respondeuBotao, setRespondeuBotao] = useState(false);

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
                    <BiChevronDownCircle size={64} />
                    <p> Começar</p>
                </div>

            </div>
            <div className="simulacao">
                <div className="pt3">
                    <h3>Insira os dados abaixo</h3>
                </div>

                {
                    respondeuBotao ?
                        <div id="teste">
                            <div className="imovel">
                                <div className="voltar">
                                    <div>
                                        <IoChevronBack size={46} />
                                        <span> Voltar </span>
                                    </div>
                                </div>
                                <section>
                                    <p>CNPJ</p>
                                    <input type="text" placeholder="00.000.000/0000-00" />
                                </section>
                                <section>
                                    <p>Renda</p>
                                    <input type="text" placeholder="ex: 5000,00" />
                                </section>
                                <section>
                                    {
                                        sabeValorImovel ?
                                            <>
                                                <p id="mudar">Valor do imóvel</p>
                                                <input id="valor" type="text" placeholder="ex:600000,00" />
                                            </>
                                            :
                                            <>
                                                <p id="mudar">Região</p>
                                                <input id="regiao" type="text" placeholder="ex:Vila Madalena" />
                                            </>
                                    }


                                </section>
                                <section>
                                    <p>Tempo do financiamento (anos)</p>
                                    <input type="text" placeholder="ex:30" />
                                </section>
                                <section>
                                    <p>Porcentagem de Renda (max:20)</p>
                                    <input type="ex:15" placeholder="ex:15" />
                                </section>
                            </div>
                        </div>
                        :
                        <div id="teste2">
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
                        </div>
                }

            </div>
            <Footer />
        </>
    );
}

export default PaginaInicial;
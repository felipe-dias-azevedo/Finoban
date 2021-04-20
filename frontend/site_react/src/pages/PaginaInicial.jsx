import React from 'react';
import Footer from '../components/Footer';
import Header from '../components/Header';

function PaginaInicial() {
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


                <div className="comecar">
                    <p> Começar</p>
                </div>


            </div>
            <div className="simulacao">
                <div className="pt3">
                    <h3>Inisira os dados abaixo</h3>
                </div>

                <div id="teste2">
                    <div className="pt4">
                        <div className="container">
                            <h3>Já sabe o valor do imóvel?</h3>
                            <button className="bt" id="sim"> Sim</button>
                            <button className="bt" id="nao"> Não</button>
                        </div>
                    </div>
                </div>
            </div>
            <Footer />
        </>
    );
}

export default PaginaInicial;
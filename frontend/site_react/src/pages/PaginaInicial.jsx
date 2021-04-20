import React from 'react';
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
        </>
    );
}

export default PaginaInicial;
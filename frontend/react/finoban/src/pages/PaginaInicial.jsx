import React from 'react';
import Header from '../components/Header';

function PaginaInicial() {
    return (
        <>
            <Header />
            <div className="box">
                <div className="imagem">
                    <div className="texto">

                        <p>Dúvidas sobre qual a melhor opção de financiamento pra você?</p>

                        <h1>Com simples passos, você terá acesso as melhores opções de financiamento! </h1>

                        <p> Começar</p>

                    </div>
                </div>
            </div>
            <div className="pt3">
                <div className="container">
                    <a href="/">Inisira os dados abaixo</a>
                </div>
            </div>
        </>
    );
}

export default PaginaInicial;
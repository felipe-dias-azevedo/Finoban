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
                    <svg width="46" height="23" viewBox="0 0 46 23" fill="none" xmlns="http://www.w3.org/2000/svg" className="down-icon">
                        <path d="M22.9639 22.9922C22.1975 22.9937 21.4548 22.7265 20.8647 22.2369L1.1849 5.81682C0.515073 5.2594 0.0938441 4.45839 0.0138792 3.59001C-0.0660857 2.72163 0.201763 1.85701 0.758501 1.18636C1.31524 0.515706 2.11526 0.0939598 2.98257 0.0138964C3.84988 -0.0661671 4.71344 0.20201 5.38326 0.759435L22.9639 15.4718L40.5446 1.28488C40.8801 1.01209 41.2661 0.808377 41.6805 0.68545C42.0949 0.562524 42.5295 0.522807 42.9592 0.568584C43.389 0.614361 43.8055 0.744726 44.1848 0.95219C44.5641 1.15965 44.8987 1.44013 45.1693 1.77748C45.4697 2.11515 45.6972 2.51129 45.8376 2.94108C45.9779 3.37087 46.0281 3.82505 45.985 4.27517C45.9419 4.72529 45.8064 5.16164 45.5871 5.5569C45.3677 5.95215 45.0691 6.29778 44.7101 6.57215L25.0303 22.4339C24.4232 22.8461 23.6957 23.0427 22.9639 22.9922Z" fill="white" />
                    </svg>
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
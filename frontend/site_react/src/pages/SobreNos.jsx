import React from 'react';
import Footer from "../components/Footer";
import Header from "../components/Header";
import CardTime from "../components/CardTime";


function SobreNos() {

    return (
        <>
            <Header />

            <div class="container">

                <h3>Sobre o Finoban</h3>

                <div>

                    O Finoban é um projeto pensado e desenvolvido por alunos da faculdade Bandtec. Com o objetivo de
                    entregar uma solução tecnológica para o mercado financeiro, focada no ramo do financiamento
                    imobiliário.

                </div>



            </div>




            <div className="boxes">


                <div className="FirstBox">
                    <CardTime

                        nome="Vinicius Carvalho"
                    />

                    <CardTime

                        nome="José Paulo"
                    />
                    <CardTime

                        nome="Catarina Carneiro"
                    />

                </div>

                <div className="SecundBox">
                    <CardTime

                        nome="Felipe Dias"
                    />
                    <CardTime

                        nome="Mario Heleno"
                    />
                    <CardTime

                        nome="Victor Barbosa"
                    />

                </div>




            </div>

            <Footer />
        </>
    );


}

export default SobreNos;



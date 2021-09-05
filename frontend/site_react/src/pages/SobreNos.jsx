import React from 'react';
import Footer from "../components/Footer";
import Header from "../components/Header";
import CardTime from "../components/CardTime";


function SobreNos(){

    return (
        <>
            <Header />

             <div class="container">

        <h3>Sobre o Finoban</h3>

         
                O Finoban é um projeto pensado e desenvolvido por alunos da faculdade Bandtec. Com o objetivo de
                entregar uma solução tecnológica para o mercado financeiro, focada no ramo do financiamento
                imobiliário.
         

    </div>
    
            <div class="boxes">

            <CardTime/>
        
            </div>
             
            <Footer />
        </>
    );


}

export default SobreNos;



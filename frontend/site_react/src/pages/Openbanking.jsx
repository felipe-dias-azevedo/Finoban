import React from 'react';
import Footer from "../components/Footer";
import Header from "../components/Header";
import Slider from "../components/Slider";


function Openbanking() {

    return (
        <>
            <Header />

                <Slider />

            <div className="box-text openbanking-text shadow">
                <h2>O que é Open Banking? </h2>
                <br />
                <br />
                <p>De modo geral, o principal objetivo do open banking é facilitar o acesso do público a operações financeiras, produtos e serviços. Para essa finalidade, são usados os dados dos próprios usuários — desde que eles permitam.</p>
            </div>
            <Footer />
        </>
    );


}

export default Openbanking;



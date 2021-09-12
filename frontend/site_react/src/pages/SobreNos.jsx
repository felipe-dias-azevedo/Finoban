import React from 'react';
import Footer from "../components/Footer";
import Header from "../components/Header";
import CardTime from "../components/CardTime";
import Jose from '../assets/images/ze.png';
import Cat from '../assets/images/cat.png';
import Mario from '../assets/images/mario.png';
import Victor from '../assets/images/victor.png';
import Vini from '../assets/images/vini.png';
import Felipe from '../assets/images/felipe.png';




function SobreNos() {

    return (
        <>
            <Header />



            <div class="sobrenos">

                <div class="boxNormal">

                    <div className="titulo">
                        <h3>Sobre o Finoban</h3>
                    </div>

                    <div className="conteudo">
                        <p>O Finoban é um projeto pensado e desenvolvido por alunos da
                            faculdade Bandtec. Com o objetivo de
                            entregar
                            uma solução tecnológica para o mercado
                            financeiro, focada no ramo do financiamento
                            imobiliário.</p>
                    </div>



                </div>




                <div className="boxes">

                    <div className="box">

                        <div className="teste">
                            <img className="img" src="../assets/images/ze.png" alt="" />
                        </div>

                        <div className="name">
                            <label>
                                <h4>Jose Paulo</h4>
                            </label>
                        </div>

                    </div>

                    <CardTime
                        nome="jossssssssssssssssss" />


                </div>  </div>



            <Footer />
        </>
    );


}

export default SobreNos;



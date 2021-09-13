import React from 'react';
import Footer from "../components/Footer";
import Header from "../components/Header";
import CardTime from "../components/CardTime";
import timeEnum from '../utils/timeEnum';




function SobreNos() {

    return (
        <>
            <Header />



            <div class="sobrenos">

                <div class="boxNormal">

                    <div className="titulo">
                        <br />
                        <h3>Sobre o Finoban</h3>
                    </div>

                    <div className="conteudo">
                        <p>O Finoban é um projeto pensado e desenvolvido por alunos da

                            faculdade Bandtec. Com o objetivo de <br />
                            entregar
                            uma solução tecnológica para o mercado
                            financeiro, focada no ramo do financiamento <br />
                            imobiliário.</p>  <br />
                    </div>



                </div>




                <div className="boxes-time">

                    <div className="parte1">

                        <CardTime name={timeEnum.VINICIUS.name}
                            img={timeEnum.VINICIUS.imageSource}
                        func={timeEnum.VINICIUS.assign}
                        />

                        <CardTime name={timeEnum.JOSE.name}
                            img={timeEnum.JOSE.imageSource}
                        func={timeEnum.JOSE.assign}
                        />

                        <CardTime name={timeEnum.CATARINA.name}
                            img={timeEnum.CATARINA.imageSource}
                        func={timeEnum.CATARINA.assign}
                        />

                    </div>
                    

                    <div className="parte2">

                        <CardTime name={timeEnum.FELIPE.name}
                            img={timeEnum.FELIPE.imageSource}
                        func={timeEnum.FELIPE.assign}
                        />

                        <CardTime name={timeEnum.MARIO.name}
                            img={timeEnum.MARIO.imageSource}
                        func={timeEnum.MARIO.assign}
                        />

                        <CardTime name={timeEnum.VICTOR.name}
                            img={timeEnum.VICTOR.imageSource}
                        func={timeEnum.VICTOR.assign}
                        />

                    </div>



                </div>







            </div>

            <Footer />
        </>
    );


}

export default SobreNos;



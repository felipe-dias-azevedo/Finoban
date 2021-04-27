import React, { useState } from 'react';
import Header from '../components/Header';
import BankCard from '../components/BankCard';
import Footer from '../components/Footer';
import { useHistory } from 'react-router';

function Simulador() {


    let data = window.history.state.state.data;
    console.log(data)

    
    return (
        <>
            <Header />
            <div className="center">
                <div className="box box-titulo center">
                    <h1>Simulador</h1>
                </div>
                <div className="box box-subtitulo center">
                    <h4>Valores dos Bancos Referente aos Dados Mencionados</h4>
                </div>
                <div className="bancos center">
                    <BankCard 
                        banco="Banco do Presil" 
                        taxa_t ={data[0].data.taxaTotal} 
                        primeira_p="3000,00" 
                        valor_f="1.200.000,00" 
                        valor_s={(data[0].data.dfi) + (data[0].data.mip)} 
                    />
                    <BankCard 
                        banco="S16 Bank" 
                        taxa_t ={data[1].data.taxaTotal} 
                        primeira_p="2000,00" 
                        valor_f="800.000,00" 
                        valor_s={(data[1].data.dfi) + (data[1].data.mip)} 
                    />
                    <BankCard 
                        banco="Banco Cifra" 
                        taxa_t ={data[2].data.taxaTotal} 
                        primeira_p="3000,00" 
                        valor_f="1.200.000,00" 
                        valor_s={(data[2].data.dfi) + (data[2].data.mip)} 
                    />
                </div>
            </div>
            <Footer />
        </>
    );
}

export default Simulador;
import React from 'react';
import Header from '../components/Header';
import BankCard from '../components/BankCard';

function Simulador() {
    return (
        <>
            <Header />
            <div className="content center">
                <div className="box box-titulo center">
                    <h1>Simulador</h1>
                </div>
                <div className="box box-subtitulo center">
                    <h4>Valores dos Bancos Referente aos Dados Mencionados</h4>
                </div>
                <div className="bancos center">
                    <BankCard banco="Banco do Presil" taxa_t ="7" primeira_p="3000,00" valor_f="1.200.000,00" valor_s="0.35" />
                    <BankCard banco="S16 Bank" taxa_t ="5" primeira_p="2000,00" valor_f="800.000,00" valor_s="0.35" />
                    <BankCard banco="Banco Cifra" taxa_t ="7" primeira_p="3000,00" valor_f="1.200.000,00" valor_s="0.92" />
                </div>
            </div>
        </>
    );
}

export default Simulador;
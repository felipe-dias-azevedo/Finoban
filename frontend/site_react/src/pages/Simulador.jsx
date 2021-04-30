import React from 'react';
import Header from '../components/Header';
import BankCard from '../components/BankCard';
import Footer from '../components/Footer';

function Simulador() {

    let data = window.history;
    if (!data.state) {
        data = [
            data = {
                data: {
                    taxaTotal: 0.19,
                    dfi: 0.0008,
                    mip: 0.0012,
                }
            },
            data = {
                data: {
                    taxaTotal: 0.07,
                    dfi: 0.0008,
                    mip: 0.0012,
                }
            },
            data = {
                data: {
                    taxaTotal: 7,
                    dfi: 0.008,
                    mip: 0.012,
                }
            }
        ];
    } else {
        data = data.state.state.data;
    }
    
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
                        taxa_t ={(data[0].data.taxaTotal*100).toFixed(2) - 12} 
                        primeira_p="3000,00" 
                        valor_f="1.200.000,00" 
                        valor_s={(((data[0].data.dfi) + (data[0].data.mip))*100).toFixed(2)} 
                    />
                    <BankCard 
                        banco="S16 Bank" 
                        taxa_t ={(data[1].data.taxaTotal*100).toFixed(2)} 
                        primeira_p="2000,00" 
                        valor_f="800.000,00" 
                        valor_s={((((data[1].data.dfi) + (data[1].data.mip))*100).toFixed(2))} 
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
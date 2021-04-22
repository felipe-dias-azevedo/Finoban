import React from 'react';
import Footer from '../components/Footer';
import Header from '../components/Header';

function Dashboard() {
    return (
        <>
            <Header />
            <div className="center">
                <div className="box box-titulo center">
                    <h1>Dashboard Comparativo</h1>
                </div>
                <div className="box box-subtitulo center">
                    <h4>Prazo de pagamento:</h4>
                </div>
                <div className="sppiner">
                    <div className="preenchida">

                    </div>
                    <div className="bola-marca">
                        
                    </div>
                </div>
            </div>
            <Footer />
        </>
    );
}

export default Dashboard;
import React, { useState } from 'react';
import CampoItem from '../components/CampoItem';
import Footer from '../components/Footer';
import Header from '../components/Header';
import { RangeStepInput } from 'react-range-step-input';
import GoogleChart from '../components/GoogleChart';
import { useHistory } from 'react-router';

function Dashboard() {
    const history = useHistory();

    const anoInicial = 2021;
    const anoFinal = anoInicial + 35;
    const [value, setValue] = useState(((anoInicial + anoFinal) / 2).toFixed());

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

                <RangeStepInput
                    min={anoInicial} max={anoFinal}
                    value={value} step={1}
                    onChange={(e) => {
                        setValue(e.target.value);
                    }}
                />
                <span className="anoFin"> {value} </span>

                <div className="box-campos">
                    <div className="box box-label center">
                        <p>Banco:</p>
                    </div>
                    <div className="box box-label center">
                        <p>Valor Imóvel:</p>
                    </div>
                </div>
                <div className="box-campos">
                    <select name="" id="">
                        <option value="1">Banco Cifra</option>
                        <option value="1">Banco do Presil</option>
                        <option value="1">S16 Bank</option>
                    </select>
                    <input type="text" name="" id="" />
                </div>
                <div className="box-campos">
                    <CampoItem label="Taxa" valor="7% a.a." />
                    <CampoItem label="Primeira Parcela" valor="R$ 3.200,00" />
                    <CampoItem label="Parcela Anterior" valor="R$ 2.500,00" />
                    <CampoItem label="Próxima Parcela" valor="R$ 2.000,00" />
                    <CampoItem label="Parcela 2027" valor="R$ 2.200,00" />
                </div>
                <div className="box-campos">
                    <CampoItem label="Valor Total do Imóvel" valor="R$ 1.050.000,00" />
                    <div className="grafico">
                        <GoogleChart />
                    </div>
                </div>
                <div className="box-campos">
                    <a href="http://localhost:8080/api-finoban/download/txt" className="botao">
                        Salvar e Baixar TXT
                    </a>
                    <button className="botao">Gostei, quero contratar</button>
                </div>
            </div>
            <Footer />
        </>
    );
}

export default Dashboard;
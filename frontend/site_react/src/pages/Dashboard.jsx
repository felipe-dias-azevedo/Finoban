import React from 'react';
import CampoItem from '../components/CampoItem';
import Footer from '../components/Footer';
import Header from '../components/Header';
import { RangeStepInput } from 'react-range-step-input';

function Dashboard() {

    const anoInicial = 2021;
    const anoFinal = anoInicial + 30;
    const [value, setValue] = React.useState((anoInicial + anoFinal) / 2);

    function onChange(e) {
        const newVal = e.target.value;
        setValue(newVal);
    }

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
                    onChange={onChange.bind(this)}
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
            </div>
            <Footer />
        </>
    );
}

export default Dashboard;
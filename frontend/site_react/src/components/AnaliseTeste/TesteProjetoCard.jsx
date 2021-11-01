import React, { useContext } from "react";
import { DarkModeContext } from "../../contexts/DarkModeContext";
import Chart from 'react-google-charts';

function BankCard(props) {

    const { isDarkEnable } = useContext(DarkModeContext);
    const chartBackgroundColor = isDarkEnable ? '#353535' : '#ffffff';
    const btnBackgroundColor = props.porcentagem_sucesso >= 80 ? '#64AE6C' : '#DA5C5C';

    const data = [
        ['Status', 'Porcentagem'],
        ['Passou', props.porcentagem_sucesso],
        ['Falhou', 100 - props.porcentagem_sucesso]
    ]

    return (
        <>
            <div className="box-card-teste shadow">
                <span className="nome-projeto-card-teste"> {props.nome_aplicacao} </span>
                <div className="btn-card-teste" style={{backgroundColor: btnBackgroundColor}}> {props.estatus ? 'Passou' : 'Falhou'} </div>
                <span className="data-card-teste"> {props.data} </span>
                <div className="grafico-torta-porcentagem">
                    <Chart
                        width={'100px'}
                        height={'100px'}
                        chartType="PieChart"
                        loader={<div>Carregando o gráfico</div>}
                        data={data}
                        options={{
                            tooltip: { textStyle: { fontSize: 10 } },
                            legend: 'none',
                            pieSliceText: 'none',
                            pieHole: 0.7,
                            slices: {
                                0: { color: '#64AE6C' },
                                1: { color: '#DA5C5C' },
                            },
                            backgroundColor: chartBackgroundColor,
                        }}
                    />
                </div>
            </div>
        </>
    );
}

export default BankCard;
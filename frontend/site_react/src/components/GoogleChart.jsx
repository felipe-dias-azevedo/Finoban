import React from 'react';
import Chart from "react-google-charts";

function GoogleChart() {
    const opcoes = {
        title: 'Gasto anual das parcelas:',
        hAxis: { title: 'Ano', titleTextStyle: { color: '#4b4b4b' } },
        vAxis: { minValue: 0, maxValue: 10 },
        // For the legend to fit, we make the chart area smaller
        chartArea: { width: '80%', height: '50%' },
        // lineWidth: 25
    };

    const data = [
        ['Ano', 'Gasto'],
        ['2021', 10],
        ['2022', 9],
        ['2023', 5],
        ['2024', 3],
    ];

    return (
        <Chart
            width={"100%"}
            height={"100%"}
            chartType="LineChart"
            loader={<span>Carregando o gráfico</span>}
            data={data}
            options={opcoes}
        />
    );
}

export default GoogleChart;
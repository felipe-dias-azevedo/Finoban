import React from 'react';
import Chart from "react-google-charts";

function GoogleChart(props) {
    const opcoes = {
        title: 'Gasto anual das parcelas:',
        hAxis: { title: 'Ano', titleTextStyle: { color: '#4b4b4b' } },
        vAxis: {minValue: 0, maxValue: 10 },
        // For the legend to fit, we make the chart area smaller
        chartArea: { width: '80%', height: '50%' },
        // lineWidth: 25
    };


    console.log(props.anos)

    let listaAnos = [];

    
    const data = [
        ['Ano', 'Gasto'],
        ['2021', 9],
        ['2022', 10],
        ['2023', 11]
    ];
    
    var contador1 = 10;
    var contador2 = 0;
    var valorPrestacao = props.valor/12
    console.log(valorPrestacao)
    for (var i = new Date().getFullYear(); i <= props.anos; i++) {
        listaAnos[i] = i;
        data[contador2+1] = [i.toString(), valorPrestacao]; 
        contador1 = contador1 - 1;
        valorPrestacao -= 798.90;
        contador2++;
    }

    console.log(data)
    console.log(listaAnos)

    var primeiraPrestacaoCifraChart = localStorage.getItem("primeiraPrestacaoCifraChart");

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
import React from 'react';
import { Chart as GoogleChart } from "react-google-charts";
import graphicEnum from '../utils/graphicEnum';
import Loading from '../images/Loading.gif';

function Chart({ id, data }) {
    switch (id) {
        case graphicEnum.DISPERSAO:
            return (
                <GoogleChart
                    width={'100%'}
                    chartType="ScatterChart"
                    loader={
                        <img src={Loading} alt="loading" className="loading" />
                    }
                    data={[
                        ["Age", "Weight"], 
                        [4, 5.5], 
                        [8, 12], 
                        [12, 24],
                        [11, 14],
                        [4, 5],
                        [3, 3.5],
                        [6.5, 7],
                    ]}
                    options={{
                        hAxis: {
                            title: 'Time',
                          },
                          vAxis: {
                            title: 'Popularity',
                          },
                        legend: { position: 'none' },
                    }}
                    legendToggle
                />
            );
        case graphicEnum.HISTOGRAMA:
            return (
                <GoogleChart
                    width={'100%'}
                    chartType="Bar"
                    loader={
                        <img src={Loading} alt="loading" className="loading" />
                    }
                    data={[
                        ['Nome', 'Quantidade'],
                        ['X', 14],
                        ['Y', 6],
                        ['Z', 2],
                        ['W', 12],
                        ['V', 8],
                    ]}
                    options={{
                        legend: { position: 'none' },
                    }}
                    rootProps={{ 'data-testid': '1' }}
                />
            );
        case graphicEnum.LINHA:
            return (
                <GoogleChart
                    width={'100%'}
                    chartType="LineChart"
                    loader={
                        <img src={Loading} alt="loading" className="loading" />
                    }
                    data={[
                        ['x', 'dogs'],
                        [0, 0],
                        [1, 10],
                        [2, 23],
                        [3, 17],
                        [4, 18],
                        [5, 9],
                        [6, 11],
                        [7, 27],
                        [8, 33],
                        [9, 40],
                        [10, 32],
                        [11, 35],
                    ]}
                    options={{
                        hAxis: {
                          title: 'Time',
                        },
                        vAxis: {
                          title: 'Popularity',
                        },
                        legend: { position: 'none' },
                    }}
                    rootProps={{ 'data-testid': '1' }}
                />
            );
        case graphicEnum.PROJECAO:
            return (
                <GoogleChart
                    width={'100%'}
                    chartType="ScatterChart"
                    loader={
                        <img src={Loading} alt="loading" className="loading" />
                    }
                    data={[
                        ['Diameter', 'Age'],
                        [8, 37],
                        [4, 19.5],
                        [11, 52],
                        [4, 22],
                        [3, 16.5],
                        [6.5, 32.8],
                        [14, 72],
                    ]}
                    options={{
                        hAxis: { title: 'Diameter' },
                        vAxis: { title: 'Age' },
                        legend: 'none',
                        trendlines: { 0: {} },
                    }}
                    rootProps={{ 'data-testid': '1' }}
                />
            );
        case graphicEnum.MAPA:
            return (
                <GoogleChart
                    width={'100%'}
                    chartType="Bar"
                    loader={
                        <img src={Loading} alt="loading" className="loading" />
                    }
                    data={[
                        ['Bairro', 'Centro', 'Consolação', 'Brooklin', 'Mooca', 'Santo Amaro', 'Interlagos'],
                        ['Tatuapé', 0,0,0,4,2,12],
                        ['C. César', 23,0,4,32,1,5],
                        ['Ibirapuera', 2,1,32,1,4,0],
                        ['Perus', 23,2,13,5,8,3],
                        ['Itaim', 2,4,7,8,0,0],
                        ['Morumbi', 4,4,4,4,4,4],
                    ]}
                    options={{
                        legend: { position: 'none' },
                    }}
                    rootProps={{ 'data-testid': '1' }}
                />
            );
        case graphicEnum.GAUGE:
            return (
                <GoogleChart
                    width={'100%'}
                    chartType="Gauge"
                    loader={
                        <img src={Loading} alt="loading" className="loading" />
                    }
                    data={[
                        ['Label', 'Value'],
                        ['CPU', 55],
                    ]}
                    options={{
                        redFrom: 90,
                        redTo: 100,
                        yellowFrom: 75,
                        yellowTo: 90,
                        minorTicks: 5,
                    }}
                    rootProps={{ 'data-testid': '1' }}
                />
            );
        default:
            return (
                <div>NO CHART</div>
            );
    }
}

export default Chart;
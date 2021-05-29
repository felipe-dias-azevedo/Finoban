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
                    data={data}
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
                    data={data}
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
                    data={data}
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
                    data={data}
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
                    data={data}
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
                    data={data}
                    options={{
                        redFrom: 85,
                        redTo: 100,
                        yellowFrom: 66,
                        yellowTo: 85,
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
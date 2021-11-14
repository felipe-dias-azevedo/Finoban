import React, { useContext } from 'react';
import { Chart as GoogleChart } from "react-google-charts";
import graphicEnum from '../../utils/graphicEnum';
import Loading from '../../assets/images/Loading.gif';
import { DarkModeContext } from '../../contexts/DarkModeContext';

function Chart({ id, data }) {

    const { isDarkEnable } = useContext(DarkModeContext);
	const chartBackgroundColor = isDarkEnable ? "#353535" : "#ffffff";

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
                        legend: { position: 'none' },
                        backgroundColor: chartBackgroundColor
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
                        backgroundColor: chartBackgroundColor
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
                        legend: { position: 'none' },
                        backgroundColor: chartBackgroundColor
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
                        legend: 'none',
                        trendlines: { 0: {} },
                        backgroundColor: chartBackgroundColor
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
                        backgroundColor: chartBackgroundColor
                    }}
                    rootProps={{ 'data-testid': '1' }}
                />
            );
        case graphicEnum.GAUGE:
            return (
                <div>
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
                            backgroundColor: chartBackgroundColor
                        }}
                        rootProps={{ 'data-testid': '1' }}
                    />
                </div>
            );
        case graphicEnum.AREA:
            return (
                <div className="chart-value">
                    <GoogleChart
                        width={'100%'}
                        chartType="AreaChart"
                        loader={
                            <img src={Loading} alt="loading" className="loading" />
                        }
                        data={data}
                        options={{
                            legend: { position: 'none' },
                            vAxis: { minValue: 0 },
                            backgroundColor: chartBackgroundColor
                        }}
                    />
                </div>
            );
        case graphicEnum.VALOR:
            return (
                <div className="chart-value">
                    <h3 className="chart-value-holder">
                        { data }
                    </h3>
                </div>
            );
        case graphicEnum.STATUS:
            const bgColor = (data === "FALHOU" ? "#DA5C5C" : "#64AE6C");
            return (
                <div className="chart-value">
                    <h3 
                        style={{ backgroundColor: bgColor, marginTop: '0.5rem', width: '50%' }} 
                        className="chart-value-holder status-health-check status-chart"
                    >
                        { data }
                    </h3>
                </div>
            );
        default:
            return (
                <div>
                    NO CHART
                </div>
            );
    }
}

export default Chart;
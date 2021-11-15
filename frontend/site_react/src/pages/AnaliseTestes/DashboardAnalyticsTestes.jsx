import { useEffect, useState } from "react";
import Chart from "../../components/AnaliseNegocio/Chart";
import ChartHolder from "../../components/AnaliseNegocio/ChartHolder";
import MovableItem from "../../components/AnaliseNegocio/MovableItem";
import Header from "../../components/Header";
import DataChartHandler from "../../utils/dataChartHandler";
import chartsPreset from '../../utils/chartsPreset';
import api from '../../services/api';
import LoadingScreenSemHeader from '../../components/LoadingScreenSemHeader.jsx';
import ErrorPage from "../../components/ErrorPage";

export default function DashboardAnalyticsTestes() {

    const [errorStatus, setErrorStatus] = useState(false);
    const [charts, setCharts] = useState(chartsPreset);
    const [dataDashboard, setDataDashboard] = useState();

    useEffect(() => {
        api.get("/tests/dashboard", {
            headers: {
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Headers': 'Origin, X-Requested-With, Content-Type, Accept'
            }
        }).then(e => {
            setDataDashboard(e.data);
            console.log("Data", e.data);
        }).catch(e => {
            console.error(e);
            setErrorStatus(true);
        });
    }, []);
    
    function returnChart(index) {
        const chart = charts[index];
        return (
            <MovableItem
                id={index}
                name={chart.name}
                setCharts={setCharts}
                moveCard={moveCardHandler}
                idGraph={chart.id}
            >
                <Chart
                    id={chart.graphic}
                    data={
                        DataChartHandler(chart.graphic, chart.id, dataDashboard)
                    } 
                />
            </MovableItem>
        )
    }
    
    function moveCardHandler(dragIndex, hoverIndex) {
        const dragItem = charts[dragIndex];

        if (dragItem) {
            setCharts((prevState => {
                const coppyChart = [...prevState];

                const oldChart = coppyChart[hoverIndex];
                coppyChart[hoverIndex] = coppyChart[dragIndex];
                coppyChart[dragIndex] = oldChart;

                return coppyChart;
            }));
        }
    }

    if (!dataDashboard && !errorStatus) {
        return (
            <>
                <Header />
                <LoadingScreenSemHeader />
            </>
        );
    } else if (errorStatus) {
        return (
            <>
                <Header />
                <ErrorPage />
            </>
        );
    }

    return (
        <>
            <Header />
            <div id="app">
                <div className="chart-holder test-chart-holder">
                    <ChartHolder id={11} moveCard={moveCardHandler}>
                        {returnChart(11)}
                    </ChartHolder>
                    <ChartHolder id={12} moveCard={moveCardHandler}>
                        {returnChart(12)}
                    </ChartHolder>
                    <ChartHolder id={13} moveCard={moveCardHandler}>
                        {returnChart(13)}
                    </ChartHolder>
                    <ChartHolder id={14} moveCard={moveCardHandler}>
                        {returnChart(14)}
                    </ChartHolder>
                    <ChartHolder id={15} moveCard={moveCardHandler}>
                        {returnChart(15)}
                    </ChartHolder>
                    <ChartHolder id={16} moveCard={moveCardHandler}>
                        {returnChart(16)}
                    </ChartHolder>
                    <ChartHolder id={17} moveCard={moveCardHandler}>
                        {returnChart(17)}
                    </ChartHolder>
                    <ChartHolder id={18} moveCard={moveCardHandler}>
                        {returnChart(18)}
                    </ChartHolder>
                    <ChartHolder id={19} moveCard={moveCardHandler}>
                        {returnChart(19)}
                    </ChartHolder>
                </div>
            </div>
        </>
    );
}
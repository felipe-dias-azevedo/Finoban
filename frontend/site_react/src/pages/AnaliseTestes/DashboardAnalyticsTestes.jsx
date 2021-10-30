import { useEffect, useState } from "react";
import Chart from "../../components/AnaliseNegocio/Chart";
import ChartHolder from "../../components/AnaliseNegocio/ChartHolder";
import MovableItem from "../../components/AnaliseNegocio/MovableItem";
import Header from "../../components/Header";
import DataChartHandler from "../../utils/dataChartHandler";

export default function DashboardAnalyticsTestes() {

    const [charts, setCharts] = useState([
        {id: 0, graphic: 2, name: "oi"}, 
        {id: 1, graphic: 2, name: "io"}
    ]);
    const [dataDashboard, setDataDashboard] = useState([
        ['x','y'],
        ['10',20],
        ['30',40]
    ]);

    useEffect(() => {
    
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
                        dataDashboard
                        // DataChartHandler(chart.graphic, chart.id, dataDashboard)
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

    return (
        <>
            <Header />
            <div id="app">
                <div className="chart-holder test-chart-holder">
                    <ChartHolder id={0} moveCard={moveCardHandler}>
                        {returnChart(0)}
                    </ChartHolder>
                    <ChartHolder id={1} moveCard={moveCardHandler}>
                        {returnChart(1)}
                    </ChartHolder>
                </div>
            </div>
        </>
    );
}
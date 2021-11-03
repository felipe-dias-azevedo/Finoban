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
                    {/* <ChartHolder id={0} moveCard={moveCardHandler}>
                        {returnChart(0)}
                    </ChartHolder>
                    <ChartHolder id={1} moveCard={moveCardHandler}>
                        {returnChart(1)}
                    </ChartHolder> */}
                    
                    {/* TODO: GRÁFICOS A SEREM INSERIDOS FUTURAMENTE */}
                    <h3>Porcentagem de sucesso por quantidade de funções</h3>
                    <h3>Porcentagem geral de sucesso</h3>
                    <h3>Quantidade de testes executados</h3>
                    <h3>Data de execução do último teste</h3>
                    <h3>Duração da execução total dos testes</h3>
                    <h3>Status geral dos testes (Passou[verde] ou Falhou[vermelho])</h3>
                    <h3>Porcentagem de sucesso por dominio (ex: controller, service, etc)</h3>
                    <h3>Tempo médio de execução por domínio</h3>
                    <h3>Tempo médio de execução por classe</h3>
                </div>
            </div>
        </>
    );
}
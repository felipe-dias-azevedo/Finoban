import React, { useState } from 'react';
import Switch from "react-switch";
import ChartHolder from '../components/ChartHolder';
import MovableItem from '../components/MovableItem';
import chartsPreset from '../utils/chartsPreset';
import Chart from '../components/Chart';

function Dashboard() {

    const [charts, setCharts] = useState(chartsPreset);
    const [chartsVisible, setChartsVisible] = useState([
        true,true,true,true,true,true,true,true,true,true,true
    ]);

    function handleSwitch(index) {
        setChartsVisible(prevState => {
            const coppyChartsVisible = [...prevState];
            coppyChartsVisible[index] = !coppyChartsVisible[index];
            return coppyChartsVisible;
        });
    }

    function returnChart(index) {
        let chart = charts[index];
        return (
            <MovableItem
                id={index}
                name={chart.name}
                setCharts={setCharts}
                moveCard={moveCardHandler}
            >
                <Chart id={chart.graphic} />
            </MovableItem>
        )
    }

    const moveCardHandler = (dragIndex, hoverIndex) => {
        console.log(dragIndex, hoverIndex);
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
        <div id="app">
            <section className="title-site">
                <h1>Dashboard</h1>
            </section>
            <section className="subtitle-site">
                <div className="title-holder">
                    <h2>Correlações</h2>
                    <div className="switches">
                        <div className="switch-holder">
                            <h3>Regiões p/ Renda</h3>
                            <Switch onChange={() => {handleSwitch(5)}} checked={chartsVisible[5]} />
                        </div>
                        <div className="switch-holder">
                            <h3>Valor Imóvel p/ Idade</h3>
                            <Switch onChange={() => {handleSwitch(6)}} checked={chartsVisible[6]} />
                        </div>
                        <div className="switch-holder">
                            <h3>Valor Imóvel p/ Renda</h3>
                            <Switch onChange={() => {handleSwitch(8)}} checked={chartsVisible[8]} />
                        </div>
                        <div className="switch-holder">
                            <h3>CEP p/ Região</h3>
                            <Switch onChange={() => {handleSwitch(9)}} checked={chartsVisible[9]} />
                        </div>
                    </div>
                </div>
                <div className="title-holder">
                    <h2>Histogramas</h2>
                    <div className="switches">
                        <div className="switch-holder">
                            <h3>Tempo de Permanência</h3>
                            <Switch onChange={() => {handleSwitch(3)}} checked={chartsVisible[3]} />
                        </div>
                        <div className="switch-holder">
                            <h3>Avaliação Site</h3>
                            <Switch onChange={() => {handleSwitch(4)}} checked={chartsVisible[4]} />
                        </div>
                        <div className="switch-holder">
                            <h3>Regiões Escolhidas</h3>
                            <Switch onChange={() => {handleSwitch(7)}} checked={chartsVisible[7]} />
                        </div>
                        <div className="switch-holder">
                            <h3>Bancos Escolhidos</h3>
                            <Switch onChange={() => {handleSwitch(10)}} checked={chartsVisible[10]} />
                        </div>
                    </div>
                </div>
                <div className="title-holder">
                    <h2>Lucro</h2>
                    <div className="switches">
                        <div className="switch-holder">
                            <h3>Rendimento Mensal</h3>
                            <Switch onChange={() => {handleSwitch(0)}} checked={chartsVisible[0]} />
                        </div>
                        <div className="switch-holder">
                            <h3>Porcentual de Perdas</h3>
                            <Switch onChange={() => {handleSwitch(1)}} checked={chartsVisible[1]} />
                        </div>
                        <div className="switch-holder">
                            <h3>Projeção Próximo Mês</h3>
                            <Switch onChange={() => {handleSwitch(2)}} checked={chartsVisible[2]} />
                        </div>
                    </div>
                </div>
            </section>
            <div className="chart-holder giant-holder">
                {chartsVisible[charts[0].id] && (
                    <ChartHolder giant id={0} moveCard={moveCardHandler}>
                        {returnChart(0)}
                    </ChartHolder>
                )}
                {chartsVisible[charts[1].id] && (
                    <ChartHolder giant id={1} moveCard={moveCardHandler}>
                        {returnChart(1)}
                    </ChartHolder>
                )}
            </div>
            <div className="chart-holder tiny-holder">
                {chartsVisible[charts[2].id] && (
                    <ChartHolder id={2} moveCard={moveCardHandler}>
                        {returnChart(2)}
                    </ChartHolder>
                )}
                {chartsVisible[charts[3].id] && (
                    <ChartHolder id={3} moveCard={moveCardHandler}>
                        {returnChart(3)}
                    </ChartHolder>
                )}
                {chartsVisible[charts[4].id] && (
                    <ChartHolder id={4} moveCard={moveCardHandler}>
                        {returnChart(4)}
                    </ChartHolder>
                )}
                {chartsVisible[charts[5].id] && (
                    <ChartHolder id={5} moveCard={moveCardHandler}>
                        {returnChart(5)}
                    </ChartHolder>
                )}
                {chartsVisible[charts[6].id] && (
                    <ChartHolder id={6} moveCard={moveCardHandler}>
                        {returnChart(6)}
                    </ChartHolder>
                )}
                {chartsVisible[charts[7].id] && (
                    <ChartHolder id={7} moveCard={moveCardHandler}>
                        {returnChart(7)}
                    </ChartHolder>
                )}
                {chartsVisible[charts[8].id] && (
                    <ChartHolder id={8} moveCard={moveCardHandler}>
                        {returnChart(8)}
                    </ChartHolder>
                )}
                {chartsVisible[charts[9].id] && (
                    <ChartHolder id={9} moveCard={moveCardHandler}>
                        {returnChart(9)}
                    </ChartHolder>
                )}
                {chartsVisible[charts[10].id] && (
                    <ChartHolder id={10} moveCard={moveCardHandler}>
                        {returnChart(10)}
                    </ChartHolder>
                )}
            </div>
        </div>
    );
}

export default Dashboard;
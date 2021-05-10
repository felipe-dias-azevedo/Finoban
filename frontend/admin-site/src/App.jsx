import React, { useState } from 'react';
import ChartHolder from './components/ChartHolder';
import MovableItem from './components/MovableItem';
import chartsPreset from './chartsPreset';
import Chart from './components/Chart';
import Switch from "react-switch";

function App() {

    const [charts, setCharts] = useState(chartsPreset);
    const [chartsVisible, setChartsVisible] = useState([
        true,true,true,true,true,true,true,true,true,true,true
    ]);

    function handleSwitch(index) {
        setChartsVisible( prevState => {
            const coppyChartsVisible = [...prevState];
            coppyChartsVisible[index] = !coppyChartsVisible[index];
            return coppyChartsVisible;
        });
        console.log(chartsVisible);
    }

    function returnChart(index) {
        let chart = charts[index];
        return (
            <MovableItem id={chart.id} name={chart.name} setCharts={setCharts} moveCard={moveCardHandler}>
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

                // const prevItem = coppiedStateArray.splice(hoverIndex, 1, dragItem);
                // coppiedStateArray.splice(dragIndex, 1, prevItem[0]);
                const oldChart = coppyChart[hoverIndex];
                coppyChart[hoverIndex] = coppyChart[dragIndex];
                coppyChart[dragIndex] = oldChart;

                return coppyChart;
            }));
        }
    }

    return (
        <div id="app">

            <Switch onChange={() => {handleSwitch(0)}} checked={chartsVisible[0]} />

            <div className="chart-holder">
                {chartsVisible[0] && (
                    <ChartHolder giant id={0} moveCard={moveCardHandler}>
                        {returnChart(0)}
                    </ChartHolder>
                )}
                <ChartHolder giant id={1} moveCard={moveCardHandler}>
                    {returnChart(1)}
                </ChartHolder>
            </div>
            <div className="chart-holder">
                <ChartHolder id={2} moveCard={moveCardHandler}>
                    {returnChart(2)}
                </ChartHolder>
                <ChartHolder id={3} moveCard={moveCardHandler}>
                    {returnChart(3)}
                </ChartHolder>
                <ChartHolder id={4} moveCard={moveCardHandler}>
                    {returnChart(4)}
                </ChartHolder>
            </div>
            <div className="chart-holder">
                <ChartHolder id={5} moveCard={moveCardHandler}>
                    {returnChart(5)}
                </ChartHolder>
                <ChartHolder id={6} moveCard={moveCardHandler}>
                    {returnChart(6)}
                </ChartHolder>
                <ChartHolder id={7} moveCard={moveCardHandler}>
                    {returnChart(7)}
                </ChartHolder>
            </div>
            <div className="chart-holder">
                <ChartHolder id={8} moveCard={moveCardHandler}>
                    {returnChart(8)}
                </ChartHolder>
                <ChartHolder id={9} moveCard={moveCardHandler}>
                    {returnChart(9)}
                </ChartHolder>
                <ChartHolder id={10} moveCard={moveCardHandler}>
                    {returnChart(10)}
                </ChartHolder>
            </div>
        </div>
    );
}

export default App;

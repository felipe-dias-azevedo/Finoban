import React, { useState } from 'react';
import ChartHolder from './components/ChartHolder';
import MovableItem from './components/MovableItem';
import chartsPreset from './chartsPreset';
import Chart from './components/Chart';

function App() {

    const [charts, setCharts] = useState(chartsPreset);

    function returnChart(index) {
        let chart = charts[index];
        return (
            <MovableItem id={chart.id} name={chart.name} setCharts={setCharts} moveCard={moveCardHandler}>
                <Chart id={chart.id} />
            </MovableItem>
        )
    }

    const moveCardHandler = (dragIndex, hoverIndex) => {
        console.log(dragIndex, hoverIndex);
        const dragItem = charts[dragIndex];

        if (dragItem) {
            setCharts((prevState => {
                const coppiedStateArray = [...prevState];

                const prevItem = coppiedStateArray.splice(hoverIndex, 1, dragItem);

                coppiedStateArray.splice(dragIndex, 1, prevItem[0]);

                return coppiedStateArray;
            }));
        }
    }

    return (
        <div id="app">
            <div className="chart-holder">
                <ChartHolder giant id={charts[0].id} moveCard={moveCardHandler}>
                    {returnChart(0)}
                </ChartHolder>
                <ChartHolder giant id={charts[1].id} moveCard={moveCardHandler}>
                    {returnChart(1)}
                </ChartHolder>
            </div>
            <div className="chart-holder">
                <ChartHolder id={charts[2].id} moveCard={moveCardHandler}>
                    {returnChart(2)}
                </ChartHolder>
                <ChartHolder id={charts[3].id} moveCard={moveCardHandler}>
                    {returnChart(3)}
                </ChartHolder>
                <ChartHolder id={charts[4].id} moveCard={moveCardHandler}>
                    {returnChart(4)}
                </ChartHolder>
            </div>
            <div className="chart-holder">
                <ChartHolder id={charts[5].id} moveCard={moveCardHandler}>
                    {returnChart(5)}
                </ChartHolder>
                <ChartHolder id={charts[6].id} moveCard={moveCardHandler}>
                    {returnChart(6)}
                </ChartHolder>
                <ChartHolder id={charts[7].id} moveCard={moveCardHandler}>
                    {returnChart(7)}
                </ChartHolder>
            </div>
            <div className="chart-holder">
                <ChartHolder id={charts[8].id} moveCard={moveCardHandler}>
                    {returnChart(8)}
                </ChartHolder>
                <ChartHolder id={charts[9].id} moveCard={moveCardHandler}>
                    {returnChart(9)}
                </ChartHolder>
                <ChartHolder id={charts[10].id} moveCard={moveCardHandler}>
                    {returnChart(10)}
                </ChartHolder>
            </div>
        </div>
    );
}

export default App;

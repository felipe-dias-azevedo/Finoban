import { useContext } from "react";
import { useState } from "react";
import { useParams } from "react-router-dom";
import Chart from "../../components/AnaliseNegocio/Chart";
import chartsPreset from '../../utils/chartsPreset';
import DataChartHandler from '../../utils/dataChartHandler';
import Header from '../../components/Header';
import Footer from '../../components/Footer';
import { NegocioContext } from '../../contexts/NegocioContext';
import LoadingScreen from "../../components/LoadingScreen";

function GraficoNegocio() {

    const params = useParams();
    const [chart, setChart] = useState(chartsPreset[params.id]);
    const { dataDashboard } = useContext(NegocioContext);

    if (!dataDashboard) {
        return (
            <LoadingScreen />
        );
    }

    return (
        <>
            <Header />
            <div id="app">
                <section className="title-site">
                    <h1>{chart.name}</h1>
                </section>
                <div className="chart specific-chart">
                    <Chart
                        id={chart.graphic}
                        data={
                            DataChartHandler(chart.graphic, chart.id, dataDashboard)
                        } 
                    />
                </div>
            </div>
            <Footer />
        </>
    );
}

export default GraficoNegocio;
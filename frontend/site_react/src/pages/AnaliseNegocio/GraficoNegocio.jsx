import { useEffect } from "react";
import { useState } from "react";
import { useHistory, useParams } from "react-router-dom";
import Chart from "../../components/AnaliseNegocio/Chart";
import chartsPreset from '../../utils/chartsPreset';
import DataChartHandler from '../../utils/dataChartHandler';
import Header from '../../components/Header';
import Footer from '../../components/Footer';

function GraficoNegocio() {

    const history = useHistory();
    const params = useParams();
    const [chart, setChart] = useState(chartsPreset[params.id]);
    const [dataDashboard, setDataDashboard] = useState();

    useEffect(() => {
        if (!sessionStorage.getItem('dataDash')) {
            history.push("/analise/dashboard");
        } else {
            // console.log(sessionStorage.getItem('dataDash'));
            setDataDashboard(JSON.parse(sessionStorage.getItem('dataDash')));
        }
    }, [])

    return (
        <>
            <Header />
            <div id="app">
                <section className="title-site">
                    <h1>{chart.name}</h1>
                </section>
                { dataDashboard && (
                    <div className={"chart specific-chart"}>
                        <Chart
                            id={chart.graphic}
                            data={
                                DataChartHandler(chart.graphic, chart.id, dataDashboard)
                            } 
                            />
                    </div>
                )}
            </div>
            <Footer />
        </>
    );
}

export default GraficoNegocio;
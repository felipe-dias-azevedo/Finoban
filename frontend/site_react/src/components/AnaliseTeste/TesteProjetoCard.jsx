import React, { useContext } from "react";
import { DarkModeContext } from "../../contexts/DarkModeContext";
import Chart from "react-google-charts";

function BankCard(props) {
	console.log(props);

	const { isDarkEnable } = useContext(DarkModeContext);
	const chartBackgroundColor = isDarkEnable ? "#353535" : "#ffffff";
	const btnBackgroundColor = props.estatus ? "#64AE6C" : "#DA5C5C";

	const dataChart = [
		["Status", "Porcentagem"],
		["Passou", props.porcentagem_sucesso * 100],
		["Falhou", 100 - props.porcentagem_sucesso * 100],
	];

	return (
		<>
			<div className="box-card-teste shadow">
				<span className="nome-projeto-card-teste">
					{" "}
					{props.nome_aplicacao}{" "}
				</span>
				<div
					className="btn-card-teste"
					style={{ backgroundColor: btnBackgroundColor }}
				>
					{" "}
					{props.estatus ? "Passou" : "Falhou"}{" "}
				</div>
				<span className="qtd-test-card-teste">
					{" "}
					{props.qtd_testes}{" "}
				</span>
				<span className="data-card-teste"> {props.data_execucao} </span>
				<span className="duracao-card-teste">
					{" "}
					{props.duracao_execucao}s{" "}
				</span>
				<div className="grafico-torta-porcentagem">
					<Chart
						width={"100px"}
						height={"100px"}
						chartType="PieChart"
						loader={<div>Carregando o gráfico</div>}
						data={dataChart}
						options={{
							tooltip: { textStyle: { fontSize: 1 } },
							legend: "none",
							pieSliceText: "none",
							pieHole: 0.7,
							slices: {
								0: { color: "#64AE6C" },
								1: { color: "#DA5C5C" },
							},
							backgroundColor: chartBackgroundColor,
						}}
					/>
				</div>
			</div>
		</>
	);
}

export default BankCard;

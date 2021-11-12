import React, { useContext } from "react";
import { Link } from "react-router-dom";
import { DarkModeContext } from "../../contexts/DarkModeContext";
import Chart from "react-google-charts";

function TesteApiPrincipalCard(props) {
	console.log(props);

	const { isDarkEnable } = useContext(DarkModeContext);
	const chartBackgroundColor = isDarkEnable ? "#353535" : "#ffffff";
	const btnBackgroundColor = props.status ? "#64AE6C" : "#DA5C5C";

	const dataChart = [
		["Status", "Porcentagem"],
		["Passou", props.porcentagem_sucesso * 100],
		["Falhou", 100 - props.porcentagem_sucesso * 100],
	];

	return (
		<>
			<Link to="/testes/arquivos">
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
						{props.status ? "Passou" : "Falhou"}{" "}
					</div>
					<span className="qtd-test-card-teste">
						{" "}
						{props.qtd_testes}{" "}
					</span>
					<span className="data-card-teste">
						{" "}
						{props.data_execucao}{" "}
					</span>
					<span className="duracao-card-teste">
						{" "}
						{props.duracao_execucao}s{" "}
					</span>
					<div className="div-grafico-teste">
						<span className="text-porcentagem-sucesso">
							{props.porcentagem_sucesso * 100}%
						</span>
						<Chart
							width={"100px"}
							height={"100px"}
							chartType="PieChart"
							loader={
								<div className="grafico-torta-porcentagem">
									Carregando o gráfico
								</div>
							}
							data={dataChart}
							options={{
								tooltip: {
									trigger: "none",
								},
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
			</Link>
		</>
	);
}

export default TesteApiPrincipalCard;

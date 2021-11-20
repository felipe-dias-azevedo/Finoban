import { useState, useEffect } from "react";
import Header from "../../components/Header";
import Footer from "../../components/Footer";
import TesteApiPrincipalCard from "../../components/AnaliseTeste/TesteApiPrincipalCard";
import TesteApiExternaCard from "../../components/AnaliseTeste/TesteApiExternaCard";
import api from "../../services/api";
import dateHelper from '../../utils/dateHelper'

function TestesProjetos() {
	const [responseProjectTest, setResponseProjectTests] = useState([]);
	const mockResponse = [
		{
			dataExecucao: "2021-10-29T20:24Z",
			duracaoExecucao: 50.74,
			nomeAplicacao: "API Finoban",
			porcentagemSucesso: 0.93,
			quantidadeTestes: 55,
			statusGeral: "PASSOU"
		}
	]

	const getTestes = async () => {
		try {
			const response = await api.get("/tests/apps");
			console.log({ responseTesteApiPrincipal: response })
			setResponseProjectTests(response.data)
		} catch (error) {
			setResponseProjectTests(mockResponse)
			console.log(error)
		}
	}

	useEffect(() => {
		getTestes();
	}, []);

	return (
		<>
			<Header />
			<div className="titulo-teste-projeto">
				<h5> Aplicação </h5>
				<h5> Status </h5>
				<h5> Testes </h5>
				<h5> Data execução </h5>
				<h5> Duração execução </h5>
				<h5> % de sucesso </h5>
			</div>

			{responseProjectTest.map((project) => (
				<TesteApiPrincipalCard
					nome_aplicacao={project.nomeAplicacao}
					status={project.statusGeral == "PASSOU" ? true : false}
					qtd_testes={project.quantidadeTestes}
					data_execucao={dateHelper(project.dataExecucao)}
					duracao_execucao={project.duracaoExecucao}
					porcentagem_sucesso={project.porcentagemSucesso}
				/>
			))}

			<TesteApiExternaCard/>

			<Footer />
		</>
	);
}

export default TestesProjetos;

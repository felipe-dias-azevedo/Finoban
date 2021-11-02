import Header from "../../components/Header";
import Footer from "../../components/Footer";
import TesteProjetoCard from "../../components/AnaliseTeste/TesteProjetoCard";
import api from "../../services/api";
import { useState } from "react";

function TestesProjetos() {

	const [responseProjectTest, setResponseProjectTests] = useState([]);

	const getTestes = async () => {
		try {
			const response = await api.get("/tests/apps");
			console.log(response);
			setResponseProjectTests(response.data[0])
		} catch (error) {
			console.log(error);
		}
	};

	getTestes();

	return (
		<>
			<Header />

			<div className="titulo-teste-projeto">
				<h5> Aplicação </h5>
				<h5> Estatus </h5>
				<h5> Testes </h5>
				<h5> Data execução </h5>
				<h5> Duração execução (s) </h5>
				<h5> % de sucesso </h5>
			</div>

			{responseProjectTest.map((project) => {
				<TesteProjetoCard
					nome_aplicacao={project.nomeAplicacao}
					estatus={project.estatus == 'Passou' ? true : false}
					qtd_testes={project.quantidadeTestes}
					data_execucao={project.dataExecucao}
					duracao_execucao={project.duracaoExecucao}
					porcentagem_sucesso={project.porcentagemSucesso}
				/>
			})}

			<Footer />
		</>
	);
}

export default TestesProjetos;

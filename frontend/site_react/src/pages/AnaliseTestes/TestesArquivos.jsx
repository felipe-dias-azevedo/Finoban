import React, { useState, useEffect } from "react";
import Header from "../../components/Header";
import Footer from "../../components/Footer";
import api from "../../services/api";
import { Container } from "react-bootstrap";
import TesteArquivoCard from "../../components/AnaliseTeste/TesteArquivoCard";

function TestesArquivos() {
	const [responseTests, setResponseTests] = useState([]);

	useEffect(() => {
		getTestes();
	}, []);

	const getTestes = async () => {
		try {
			const response = await api.get("/tests/apps/0");
			setResponseTests(response.data);
		} catch (error) {
			setResponseTests(mock);
			console.log(error);
		}
	};

	const mock = [
		{
			nomeClasseTeste: "AcessoController",
			porcentagemSucesso: 0.63,
			quantidadeFuncoes: 8,
			duracaoExecucao: 0.23,
		},
		{
			nomeClasseTeste: "DateHelper",
			porcentagemSucesso: 1.0,
			quantidadeFuncoes: 6,
			duracaoExecucao: 0.08,
		},
		{
			nomeClasseTeste: "CadastroController",
			porcentagemSucesso: 1.0,
			quantidadeFuncoes: 17,
			duracaoExecucao: 1.05,
		},
		{
			nomeClasseTeste: "LoginController",
			porcentagemSucesso: 0.75,
			quantidadeFuncoes: 4,
			duracaoExecucao: 0.17,
		},
		{
			nomeClasseTeste: "TokenService",
			porcentagemSucesso: 1.0,
			quantidadeFuncoes: 2,
			duracaoExecucao: 2.73,
		},
		{
			nomeClasseTeste: "RegiaoController",
			porcentagemSucesso: 1.0,
			quantidadeFuncoes: 9,
			duracaoExecucao: 0.22,
		},
		{
			nomeClasseTeste: "NumberHelper",
			porcentagemSucesso: 1.0,
			quantidadeFuncoes: 3,
			duracaoExecucao: 0.01,
		},
		{
			nomeClasseTeste: "GravaCsvController",
			porcentagemSucesso: 1.0,
			quantidadeFuncoes: 1,
			duracaoExecucao: 0.03,
		},
		{
			nomeClasseTeste: "GravaArquivoController",
			porcentagemSucesso: 1.0,
			quantidadeFuncoes: 1,
			duracaoExecucao: 0.05,
		},
		{
			nomeClasseTeste: "DownloadController",
			porcentagemSucesso: 1.0,
			quantidadeFuncoes: 2,
			duracaoExecucao: 0.8,
		},
		{
			nomeClasseTeste: "FinanciamentoApisExternasController",
			porcentagemSucesso: 1.0,
			quantidadeFuncoes: 1,
			duracaoExecucao: 45.35,
		},
		{
			nomeClasseTeste: "FinobanApplications",
			porcentagemSucesso: 1.0,
			quantidadeFuncoes: 1,
			duracaoExecucao: 0.02,
		},
	];

	return (
		<>
			<Header />
			<Container>
				<div className="table-header">
					<h1>Arquivo</h1>
					<h1>Porcent. de sucesso</h1>
					<h1>Funções</h1>
					<h1>Tempo de Execução</h1>
				</div>

				<div>
					{responseTests.map((test) => (
						<TesteArquivoCard
							nomeArquivo={test.nomeClasseTeste}
							porcentagemSucesso={test.porcentagemSucesso}
							quantidadeFuncoes={test.quantidadeFuncoes}
							tempoExecucao={test.duracaoExecucao}
						/>
					))}
				</div>
			</Container>
			<Footer />
		</>
	);
}

export default TestesArquivos;

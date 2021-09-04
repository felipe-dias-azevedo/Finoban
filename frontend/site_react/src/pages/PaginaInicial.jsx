import React, { useEffect, useState } from "react";
import Footer from "../components/Footer";
import Header from "../components/Header";
import { IoChevronBack } from "react-icons/io5";
import { IoIosArrowDropdown } from "react-icons/io";
import { useHistory } from "react-router";
import api from "../services/api";
import CpfCnpj from "@react-br-forms/cpf-cnpj-mask";
import { Link as LinkScroll, animateScroll as Scroll } from "react-scroll";
import BackgroundHomeImage from '../assets/images/home-background-image.png';

function PaginaInicial() {
	const history = useHistory();

	const [sabeValorImovel, setSabeValorImovel] = useState(null);
	const [respondeuBotao, setRespondeuBotao] = useState(false);

	const [cnpj, setCnpj] = useState("");
	const [mask, setMask] = useState("");
	const [renda, setRenda] = useState("");
	const [valorImovel, setValorImovel] = useState("");
	const [tempoFinanciamento, setTempoFinanciamento] = useState("");
	const [porcentagemRenda, setPorcentagemRenda] = useState("");
	const [imoveisList, setImoveisList] = useState([]);

	const [erroCnpj, setErroCnpj] = useState("");
	const [erroRenda, setErroRenda] = useState("");
	const [erroValorImovel, setErroValorImovel] = useState("");
	const [erroTempoFinanciamento, setErroTempoFinanciamento] = useState("");
	const [erroPorcentagemRenda, setErroPorcentagemRenda] = useState("");

	var data = new Date();
	var dia = String(data.getDate()).padStart(2, "0");
	var mes = String(data.getMonth() + 1).padStart(2, "0");
	var ano = data.getFullYear();

	var hora = data.getHours();
	var minutos = data.getMinutes();
	if (minutos < 10) {
		minutos = "0" + minutos;
	}
	var segundos = data.getUTCSeconds();
	if (segundos < 10) {
		segundos = "0" + segundos;
	}
	var milisegundos = data.getMilliseconds();

	var dataAtual =
		ano +
		"-" +
		mes +
		"-" +
		dia +
		"T" +
		hora +
		":" +
		minutos +
		":" +
		segundos +
		"." +
		milisegundos;

	var horarioEntrada = sessionStorage.setItem("horarioEntrada", dataAtual);

	useEffect(() => {
		api.get("/regioes")
			.then((e) => {
				const imoveis = e.data;
				if (e.status === 200) {
					setImoveisList(imoveis);
				}
			})
			.catch((e) => {
				console.error(e);
			});
	}, []);

	async function handleSimulacao() {
		if (cnpj.trim() === "") {
			setErroCnpj("Insira um CNPJ válido");
		}
		if (renda.trim() === "") {
			setErroRenda("Insira uma renda válida");
		}
		if (valorImovel.trim() === "") {
			setErroValorImovel("Insira um valor válido");
		}
		if (tempoFinanciamento.trim() === "") {
			setErroTempoFinanciamento("Insira o tempo do financiamento");
		}
		if (porcentagemRenda.trim() === "") {
			setErroPorcentagemRenda("Insira a porcentagem da renda");
		} else {
            setErroCnpj("");
            setErroRenda("");
            setErroValorImovel("");
            setErroTempoFinanciamento("");
            setErroPorcentagemRenda("");

			const dataSimulador = {
				cnpj: "123",
				renda: parseInt(renda),
				valorImovel: valorImovel,
				tempoFinanciamento: parseInt(tempoFinanciamento),
			};

			console.log(dataSimulador);
			var porcentagemRecebida = localStorage.setItem(
				"porcentagemRenda",
				porcentagemRenda
			);

			await api
				.post("/financiamento", dataSimulador, {})
				.then((e) => {
					console.log(e.data);
					var respostaSimulacao = e.data;
					if (e.status == 200) {
						var respostaFinanciamento = localStorage.setItem(
							"respostaFinanciamento",
							JSON.stringify(respostaSimulacao)
						);
						history.push({
							pathname: "/simulador",
							state: { data: dataSimulador },
						});
					}
				})
				.catch((e) => {
					console.error(e);
					alert("Ocorreu um erro!");
				});

			const dados = dataSimulador;

			var dadosSimulador = localStorage.setItem(
				"testeChave",
				JSON.stringify(dados)
			);
		}
	}

	return (
		<>
			<Header />
			<div className="image-home">
				<div className="image-home-total">
					<div className="image-file">
						<img src={BackgroundHomeImage} alt="família feliz ao ter financiamentos" />
					</div>
					<div className="image-home-text">
						<div className="image-home-holder">
							<h2>Dúvidas sobre qual a melhor opção de financiamento pra você?</h2>
						</div>
						<div className="image-home-holder">
							<h1>
								Com simples passos, você terá acesso as 
								<b>{" "}melhores opções{" "}</b>
								de financiamento!
							</h1>
						</div>
						<div className="image-home-holder">
							<LinkScroll
								activeClass="scroll-start"
								to="start-simulation"
								spy
								smooth
								offset={-70}
								duration={500}
							>
								<IoIosArrowDropdown size={46} className="ml-30" />
								<h3>Começar</h3>
							</LinkScroll>
						</div>
					</div>
				</div>
			</div>
			<div id="start-simulation">
				<div className="simulation-title">
					<h2>Insira os dados abaixo</h2>
				</div>

				{respondeuBotao ? (
					<div className="option-value shadow">
						<div className="option-value-back">
							<nav onClick={() => setRespondeuBotao(false)}>
								<IoChevronBack size={46} />
								<h5>Voltar</h5>
							</nav>
						</div>
						<div className="option-values-fulfill">
							<section>
								<p>CNPJ</p>
								<CpfCnpj
									placeholder="00.000.000/0000-00"
									type="tel"
									value={cnpj}
									onChange={(e, type) => {
										setCnpj(e.target.value);
										setMask(type === "CNPJ");
									}}
								/>

								<label className="label-erro">{erroCnpj}</label>
							</section>
							<section>
								<p>Renda</p>
								<input
									type="text"
									placeholder="ex: 5000,00"
									onChange={(e) => setRenda(e.target.value)}
								/>
								<label className="label-erro">
									{erroRenda}
								</label>
							</section>
							<section>
								{sabeValorImovel ? (
									<>
										<p id="mudar">Valor do imóvel</p>
										<input
											id="valor"
											type="text"
											placeholder="ex: 600000,00"
											onChange={(e) =>
												setValorImovel(e.target.value)
											}
										/>

										<label className="label-erro">
											{erroValorImovel}
										</label>
									</>
								) : (
									<>
										<p id="mudar">Região</p>
										<select
											id="select-imoveis"
											onChange={(e) => {
												setValorImovel(e.target.value);
											}}
										>
											<option value="">
												-- Selecione --
											</option>

											{imoveisList.map((imovel) => {
												return (
													<option
														key={imovel.idRegiao}
														value={
															imovel.valorRegiao
														}
													>
														{imovel.descricaoRegiao}
													</option>
												);
											})}
										</select>
									</>
								)}
							</section>
							<section>
								<p>Tempo financiamento</p>
								<input
									t
									ype="text"
									placeholder="ex: 30"
									maxLength="2"
									onChange={(e) =>
										setTempoFinanciamento(e.target.value)
									}
								/>
								<label className="label-erro">
									{erroTempoFinanciamento}
								</label>
							</section>
							<section>
								<p>Porcentagem de Renda</p>
								<input
									type="text"
									placeholder="ex: 15"
									maxLength="3"
									onChange={(e) =>
										setPorcentagemRenda(e.target.value)
									}
								/>
								<label className="label-erro">
									{erroPorcentagemRenda}
								</label>
							</section>
						</div>
						<div className="option-value-simulate">
							<button
								className="bt-simular"
								onClick={handleSimulacao}
								required
							>
								Simular
							</button>
						</div>
					</div>
				) : (
					<div className="option-value shadow">
						<h3>Já sabe o valor do imóvel?</h3>
						<section>
							<button
								onClick={() => {
									setSabeValorImovel(true);
									setRespondeuBotao(true);
								}}
							>
								Sim
							</button>
							<button
								onClick={() => {
									setSabeValorImovel(false);
									setRespondeuBotao(true);
								}}
							>
								Não
							</button>
						</section>
					</div>
				)}
			</div>
			{/* <Footer /> */}
		</>
	);
}

export default PaginaInicial;

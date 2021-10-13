import React, { useEffect, useState } from "react";
import Footer from "../components/Footer";
import Header from "../components/Header";
import { IoChevronBack } from "react-icons/io5";
import { IoIosArrowDropdown } from "react-icons/io";
import { useHistory } from "react-router";
import api from "../services/api";
import { Link as LinkScroll, animateScroll as Scroll } from "react-scroll";
import BackgroundHomeImage from "../assets/images/home-background-image.png";
import UseForm from "../components/UseForm";
import validate from "../components/ValidacaoFormInicial";
import configurarToast from "../utils/toastService";
import { toast } from "react-toastify";
import ModalAviso from "../components/Toastify";
import LoadingScreen from "../components/LoadingScreenSemHeader";
import respostaEnum from "../utils/respostaEnum";

const Form = () => {
	const { values, errors, handleChange, handleSubmit } = UseForm(
		buscarTaxas,
		validate
	);

	const history = useHistory();
	const [sabeValorImovel, setSabeValorImovel] = useState(null);
	const [respondeuBotao, setRespondeuBotao] = useState(false);
	const [mask, setMask] = useState("");
	const [imoveisList, setImoveisList] = useState([]);
	const [resposta, setResposta] = useState(respostaEnum.NAO_REQUISITADO);

	const handler = (event) => {
		if (event.key == "Enter") {
			if (validate.errors) {
				buscarTaxas();
			}
		}
	};

	useEffect(() => {
		api.get("/regioes")
			.then((e) => {
				const imoveis = e.data.data;
				if (e.status === 200) {
					setImoveisList(imoveis);
				}
			})
			.catch((e) => {
				console.error(e);
			});
	}, []);

	function buscarTaxas() {
		<LoadingScreen />

		const dataSimulador = {
			cnpj: "123",
			renda: parseInt(values.renda),
			valorImovel: values.valorImovel,
			tempoFinanciamento: parseInt(values.tempoFinanciamento),
		};

		sessionStorage.setItem("dadosSimulador", JSON.stringify(dataSimulador));

		api.post("/financiamento", dataSimulador, {})
			.then((e) => {
				setResposta(respostaEnum.ESPERANDO);
				console.log(e.data);
				var respostaSimulacao = e.data;
				if (e.status == 200) {
					setTimeout(() => {
						setResposta(respostaEnum.SUCESSO);
						history.push({
							pathname: "/simulador",
						});
					}, 5000);
					var respostaFinanciamento = sessionStorage.setItem(
						"respostaFinanciamento",
						JSON.stringify(respostaSimulacao)
					);
				}
			})
			.catch((e) => {
				console.error(e);
				setResposta(respostaEnum.ERROR);
				toast.error("Ocorreu um erro ao realizar a requisição!");
			});
	}

	if (resposta === respostaEnum.ESPERANDO) {
		return <LoadingScreen />;
	}

	return (
		<>
			<div className="image-home">
				<div className="image-home-total">
					<div className="image-file">
						<img
							src={BackgroundHomeImage}
							alt="família feliz ao ter financiamentos"
						/>
					</div>
					<div className="image-home-text">
						<div className="image-home-holder">
							<h2>
								Dúvidas sobre qual a melhor opção de
								financiamento pra você?
							</h2>
						</div>
						<div className="image-home-holder">
							<h1>
								Com simples passos, você terá acesso as
								<b> melhores opções </b>
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
								<IoIosArrowDropdown
									size={46}
									className="ml-30"
								/>
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
						<form onSubmit={handleSubmit} className="form-holder">
							<div className="option-values-fulfill">
								<p>CPF</p>
								{/* <CpfCnpj
									className={`input mb-4 ${
										errors.cpf && "is-danger"
									}`}
									placeholder="000.000.000-00"
									type="text"
									maxLength="14"
									id="cpf"
									name="cpf"
									defaultValue={values.cpf || ""}
									onCh	ange={(type) => setMask(type == "CPF")}
									onKeyPress={(e) => handler(e)}
								/> */}
								<input
									className={`input mb-4 ${
										errors.cpf && "is-danger"
									}`}
									type="text"
									placeholder="ex: 5000,00"
									maxLength="10"
									id="cpf"
									name="cpf"
									value={values.cpf}
									onChange={handleChange}
									onKeyPress={(e) => handler(e)}
								/>
								{errors.cpf && (
									<p className="text-danger mb-3">{errors.cpf}</p>
								)}
								<p>Renda</p>
								<input
									className={`input mb-4 ${
										errors.renda && "is-danger"
									}`}
									type="text"
									placeholder="ex: 5000,00"
									maxLength="10"
									id="renda"
									name="renda"
									value={values.renda}
									onChange={handleChange}
									onKeyPress={(e) => handler(e)}
								/>
								{errors.renda && (
									<p className="text-danger mb-3">
										{errors.renda}
									</p>
								)}
								{sabeValorImovel ? (
									<>
										<p id="mudar">Valor do imóvel</p>
										<input
											className={`input mb-4 ${
												errors.valorImovel &&
												"is-danger"
											}`}
											id="valorImovel"
											name="valorImovel"
											type="text"
											placeholder="ex: 600000,00"
											defaultValue={
												values.valorImovel || ""
											}
											onChange={handleChange}
											onKeyPress={(e) => handler(e)}
										/>
										{errors.valorImovel && (
											<p className="text-danger mb-3">
												{errors.valorImovel}
											</p>
										)}
											
									</>
								) : (
									<>
										<p id="mudar">Região</p>
										<select
											id="select-imoveis"
											defaultValue={
												values.valorImovel || ""
											}
											onChange={handleChange}
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
								<p>Tempo financiamento</p>
								<input
									className={`input mb-4 ${
										errors.tempoFinanciamento && "is-danger"
									}`}
									type="tel"
									placeholder="ex: 30"
									maxLength="2"
									id="tempoFinanciamento"
									name="tempoFinanciamento"
									defaultValue={
										values.tempoFinanciamento || ""
									}
									onChange={handleChange}
									onKeyPress={(e) => handler(e)}
								/>
								{errors.tempoFinanciamento && (
									<p className="text-danger mb-3">
										{errors.tempoFinanciamento}
									</p>
								)}
								<p>Porcentagem de Renda</p>
								<input
									className={`input mb-4 ${
										errors.porcentagemRenda && "is-danger"
									}`}
									type="text"
									placeholder="ex: 15"
									maxLength="3"
									id="porcentagemRenda"
									name="porcentagemRenda"
									defaultValue={values.porcentagemRenda || ""}
									onChange={handleChange}
									onKeyPress={(e) => handler(e)}
								/>
								{errors.porcentagemRenda && (
									<p className="text-danger mb-3">
										{errors.porcentagemRenda}
									</p>
								)}
							</div>
							<div className="option-value-simulate">
								<button className="bt-simular" required>
									Simular
								</button>
							</div>
						</form>
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
		</>
	);
};

function PaginaInicial() {
	return (
		<>
			<Header />
			<ModalAviso />
			<Form />
			<Footer />
		</>
	);
}

export default PaginaInicial;

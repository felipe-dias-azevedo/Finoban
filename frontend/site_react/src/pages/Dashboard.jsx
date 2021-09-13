import React, { useState } from "react";
import CampoItem from "../components/CampoItem";
import Footer from "../components/Footer";
import Header from "../components/Header";
import { RangeStepInput } from "react-range-step-input";
import GoogleChart from "../components/GoogleChart";
import { useHistory } from "react-router";
import Like from "../assets/images/like.svg";
import Deslike from "../assets/images/deslike.svg";
import BtnClose from "../assets/images/btn-close.svg";
import Api from "../services/api";
import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";
import FormGroup from "react-bootstrap/FormGroup";
import { mockComponent } from "react-dom/test-utils";
import NumberFormat from "react-number-format";

function ModalFeedback(props) {
	const [feedback, setFeedback] = useState("");

	function avaliacaoLike() {
		const dataAvaliacao = {
			avalPositivo: 0,
			feedbackAval: feedback,
			fkAcesso: {
				idEntrada: 13,
			},
		};

		Api.post("/avaliacao", dataAvaliacao, {})
			.then((e) => {
				console.log(e.data);
				if (e.status === 201) {
					console.log("ok");
					props.onHide();
				}
			})
			.catch((e) => {
				console.error(e);
			});
	}

	return (
		<Modal
			{...props}
			size="xs"
			aria-labelledby="contained-modal-title-vcenter"
			centered
		>
			<Modal.Header closeButton>
				<Modal.Title id="contained-modal-title-vcenter">
					Nos dê um feedback
				</Modal.Title>
			</Modal.Header>
			<Modal.Body>
				<Form.Group controlId="exampleForm.ControlTextarea1">
					<Form.Label name="feedback" v>
						Digite sua mensagem
					</Form.Label>
					<Form.Control
						as="textarea"
						rows={2}
						name="feedback"
						onChange={(e) => setFeedback(e.target.value)}
					/>
				</Form.Group>
			</Modal.Body>
			<Modal.Footer>
				<div
					onClick={props.onHide}
					className="btn-avaliacao-cancelar"
				>
					Fechar
				</div>
				<div
					onClick={avaliacaoLike}
					className="btn-avaliacao-enviar"
				>
					Enviar
				</div>
			</Modal.Footer>
		</Modal>
	);
}

function ModalContratar(props) {
	const [modalShowSucesso, setModalShowSucesso] = React.useState(false);
	const [modalShowContratar, setModalShowContratar] = React.useState(false);

	function IrWhatsapp() {
		window.location.href = "https://wa.me/551131758248";
	}

	function IrEmail() {
		window.location.href = "mailto: safra@safra.com.br";
	}

	return (
		<Modal
			{...props}
			size="lg"
			aria-labelledby="contained-modal-title-vcenter"
			centered
		>
			<Modal.Header closeButton>
				<Modal.Title>
					<p className="cor-verde fonte-20">
						De que forma você gostaria de entrar em contato?
					</p>
				</Modal.Title>
			</Modal.Header>
			<Modal.Body>
				<Form.Group controlId="exampleForm.ControlTextarea1">
					<div className="d-flex justify-content-center">
						<Form.Label className="cor-verde fonte-15">
							Gostaria de agendar um horário?{" "}
							<input type="checkbox" id="agendar-horario"></input>
							<br />
							<div className="mt-3" />
							<input type="date" className="input-data"></input>
							<input type="time" id="input-hora"></input>
						</Form.Label>
					</div>
					<br />

					<div className="d-flex flex-row">
						<div className="btn-contato" onClick={() => {}}>
							Telefone
						</div>
						<div
							className="btn-contato"
							onClick={() => IrWhatsapp()}
						>
							WhatsApp
						</div>
						<div
							className="btn-contato"
							onClick={() => IrEmail()}
						>
							E-mail
						</div>
						<div className="btn-contato">
							Visita na Agência
						</div>
					</div>

					<ModalSucesso
						show={modalShowSucesso}
						onHide={() => setModalShowSucesso(false)}
					/>
				</Form.Group>
			</Modal.Body>
		</Modal>
	);
}

function ModalSucesso(props) {
	return (
		<Modal
			{...props}
			size="lg"
			aria-labelledby="contained-modal-title-vcenter"
			centered
		>
			<Modal.Header closeButton>
				<Modal.Title>
					<p className="cor-verde">Parabéns!!</p>
				</Modal.Title>
			</Modal.Header>
			<Modal.Body>
				<Form.Group controlId="exampleForm.ControlTextarea1">
					<div className="d-flex justify-content-center">
						<Form.Label className="cor-verde">
							O Banco Cifra irá entrar em contato com você!
							<br />
						</Form.Label>
					</div>
					<br />
				</Form.Group>
			</Modal.Body>
		</Modal>
	);
}

function Dashboard() {
	// Pegando dados do localStorage
	var objDashboard = JSON.parse(localStorage.getItem("objDashboard"));
	console.log(objDashboard);
	var porcentagemRenda = localStorage.getItem("porcentagemRenda");
	var dadosUsuario = localStorage.getItem("dadosUsuario");

	const history = useHistory();
	const [modalShowFeedback, setModalShowFeedback] = React.useState(false);
	const [modalShowContratar, setModalShowContratar] = React.useState(false);
	const anoInicial = new Date().getFullYear();
	const anoFinal = objDashboard
		? anoInicial + objDashboard.tempoFinanciamento
		: anoInicial + 30;
	const [value, setValue] = useState(((anoInicial + anoFinal) / 2).toFixed());

	window.onbeforeunload = confirmExit;
	function confirmExit() {
		efetuarLogoff();
		reqAcesso(1);
		return "You have attempted to leave this page.  If you have made any changes to the fields without clicking the Save button, your changes will be lost.  Are you sure you want to exit this page?";
	}

	function efetuarLogoff() {
		const req = {
			email: dadosUsuario.email,
		};

		Api.post("/logoff", req, {})
			.then((e) => {
				console.log(e.data);
				if (e.status == 200) {
				}
			})
			.catch((e) => {
				console.error(e);
			});
	}

	// Data
	var data = new Date();
	var dia = String(data.getDate()).padStart(2, "0");
	var mes = String(data.getMonth() + 1).padStart(2, "0");
	var ano = data.getFullYear();
	var hora = data.getHours();
	var minutos = data.getMinutes();
	if (minutos < 10) minutos = "0" + minutos;
	var segundos = data.getUTCSeconds();
	if (segundos < 10) segundos = "0" + segundos;
	var milisegundos = data.getMilliseconds();
	var dataSaida =
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

	const [selectValue, setSelectValue] = useState(1);
	const listaBancos = [
		{ id: 1, name: "Banco Cifra" },
		{ id: 2, name: "Banco do Presil" },
		{ id: 3, name: "Banco S16" },
	];

	function handleCreate(e) {
		e.preventDefault();
		alert(selectValue);
	}

	let financiamentoCifra = financiar(
		objDashboard.valorImovel,
		objDashboard.taxa3 / 25,
		objDashboard.tempoFinanciamento
	);

	let financiamentoPresil = financiar(
		objDashboard.valorImovel,
		objDashboard.taxa1 / 29,
		objDashboard.tempoFinanciamento
	);
	let financiamentoS16 = financiar(
		objDashboard.valorImovel,
		objDashboard.taxa2 / 30,
		objDashboard.tempoFinanciamento
	);

	var taxaSimulacao;
	var valorFinalImovel;
	var valorPrimeiraParcela;
	var nomeBanco;
	var proximaParcela;
	var proximaParcelaFormatado;
	var ultimaParcela;
	var ultimaParcelaFormatado;
	var prestacaoChart;

	if (selectValue == 1) {
		prestacaoChart = financiamentoCifra.prestacoes[0];
		taxaSimulacao = objDashboard.taxa3;
		valorFinalImovel = objDashboard.valorImovelCifraFormatado;
		valorPrimeiraParcela = objDashboard.valorPrimeiraPrestacaoCifra;
		proximaParcela = financiamentoCifra.prestacoes[1] / 12;
		proximaParcelaFormatado = proximaParcela.toLocaleString("pt-br", {
			style: "currency",
			currency: "BRL",
		});
		ultimaParcela =
			financiamentoCifra.prestacoes[financiamentoCifra.prestacoes.length - 1] / 12;
		ultimaParcelaFormatado = ultimaParcela.toLocaleString("pt-br", {
			style: "currency",
			currency: "BRL",
		});
		nomeBanco = "Banco Cifra";
	}

	if (selectValue == 2) {
		prestacaoChart = financiamentoPresil.prestacoes[0];
		taxaSimulacao = objDashboard.taxa1;
		valorFinalImovel = objDashboard.valorImovelPresilFormatado;
		valorPrimeiraParcela = objDashboard.valorPrimeiraPrestacaoPresil;
		proximaParcela = financiamentoPresil.prestacoes[1] / 12;
		proximaParcelaFormatado = proximaParcela.toLocaleString("pt-br", {
			style: "currency",
			currency: "BRL",
		});
		ultimaParcela = financiamentoPresil.prestacoes[financiamentoCifra.prestacoes.length - 1] / 12;
		ultimaParcelaFormatado = ultimaParcela.toLocaleString("pt-br", {
		style: "currency",
		currency: "BRL",
	});
		nomeBanco = "Banco do Presil";
	}

	if (selectValue == 3) {
		prestacaoChart = financiamentoS16.prestacoes[0];
		taxaSimulacao = objDashboard.taxa3;
		valorFinalImovel = objDashboard.valorImovelS16Formatado;
		valorPrimeiraParcela = objDashboard.valorPrimeiraPrestacaoS16;
		proximaParcela = financiamentoS16.prestacoes[1] / 12;
		proximaParcelaFormatado = proximaParcela.toLocaleString("pt-br", {
			style: "currency",
			currency: "BRL",
		});
		ultimaParcela = financiamentoS16.prestacoes[financiamentoS16.prestacoes.length - 1] / 12;
		ultimaParcelaFormatado = ultimaParcela.toLocaleString("pt-br", {
		style: "currency",
		currency: "BRL",
	});
		nomeBanco = "Banco S16";
	}

	taxaSimulacao = taxaSimulacao + "% a.a.";

	function financiar(valor_imovel, valor_taxa_juros, anos_a_serem_pagos) {
		var imovel = valor_imovel;
		var taxa_juros = valor_taxa_juros;
		var valor_a_pagar = 0;
		var anos_pagos = 0;
		var prestacoes = [];
		var prestacao_sem_taxa;
		var prestacao_com_taxa;
		var primeira_prestacao;

		while (anos_pagos < anos_a_serem_pagos) {
			anos_pagos++;
			prestacao_sem_taxa = imovel / anos_a_serem_pagos;
			prestacao_com_taxa = imovel * taxa_juros;
			if (anos_pagos == 1) {
				primeira_prestacao =
					prestacao_com_taxa / 12 + prestacao_sem_taxa / 12;
			}
			imovel = imovel - prestacao_sem_taxa;
			valor_a_pagar += prestacao_sem_taxa + prestacao_com_taxa;
			prestacoes.push(prestacao_sem_taxa + prestacao_com_taxa);
		}
		const data_financiamento = {
			valor_a_pagar: valor_a_pagar,
			primeira_prestacao: primeira_prestacao,
			prestacoes: prestacoes,
			imovel: imovel,
		};

		return data_financiamento;
	}

	function alterarSlider(indice) {
		var testeproxima = financiamentoCifra.prestacoes[indice] / 12;
		proximaParcelaFormatado = testeproxima.toLocaleString("pt-br", {
			style: "currency",
			currency: "BRL",
		});
		console.log("proxima parcela formatado");
		console.log(proximaParcelaFormatado);
	}

	function reqAcesso(confirmouContratacao) {
		var acesso = {
			// dataHoraEntrada: horarioEntrada,
			dataHoraSaida: dataSaida,
			paginaSaida: 4,
			statusSaida: confirmouContratacao,
			renda: "",
			valorImovel: "",
			tempoFinanciamento: objDashboard.tempoFinanciamento,
			porcentagemRenda: parseInt(porcentagemRenda),
			bancoEscolhido: 2,
			fkRegiao: {
				idRegiao: 15,
			},
			fkCliente: {
				id: parseInt(dadosUsuario.id),
			},
		};

		console.log(acesso);

		Api.post("/acessos", acesso, {})
			.then((e) => {
				console.log(e.data);
			})
			.catch((e) => {
				console.error(e);
			});
	}

	function avaliacaoDeslike() {
		const dataAvaliacao = {
			avalPositivo: 1,
			feedbackAval: "Deslike",
			fkAcesso: {
				idEntrada: 6,
			},
		};

		Api.post("/avaliacao", dataAvaliacao, {})
			.then((e) => {
				console.log(e.data);
				if (e.status === 201) {
					console.log("ok");
					alert("Recebemos a sua avaliação, obrigado!");
				} else {
					console.log("erro");
				}
			})
			.catch((e) => {
				console.error(e);
			});
	}

	return (
		<>
			<Header />
			<div className="center">
				<div className="box box-titulo center">
					<h1>Dashboard Comparativo</h1>
				</div>
				<div className="box box-subtitulo center">
					<h4>Prazo de pagamento:</h4>
				</div>

				<RangeStepInput
					min={anoInicial}
					max={anoFinal}
					value={value}
					step={1}
					onChange={(e) => {
						setValue(e.target.value);
						alterarSlider(value - new Date().getFullYear());
					}}
				/>
				<span className="anoFin">{value} </span>

				<div className="box-campos">
					<div className="box box-label center">
						<p className="fw-500 m-0">Banco:</p>
					</div>
					<div className="box box-label center">
						<p class="fw-500 m-0">Valor Imóvel:</p>
					</div>
				</div>
				<div className="box-campos">
					<select
						value={selectValue}
						onChange={(e) => setSelectValue(e.target.value)}
					>
						{listaBancos.map((item, index) => (
							<option value={item.id}>{item.name}</option>
						))}
					</select>
					<input
						type="text"
						value={objDashboard.valorImovelFormatado}
						disabled
					/>
				</div>
				<div className="box-campos">
					<CampoItem label="Taxa" valor={taxaSimulacao} />
					<CampoItem
						label="Primeira Parcela"
						valor={valorPrimeiraParcela}
					/>
					<CampoItem
						label="Próxima Parcela"
						valor={proximaParcelaFormatado}
					/>
					<CampoItem
						label="Última Parcela"
						valor={ultimaParcelaFormatado}
					/>
				</div>
				<div className="box-campos">
					<div className="box box-item center">
						<p>Valor final do imóvel</p>
						<br />
						<p className="fw-600">{valorFinalImovel}</p>
					</div>
					<div className="grafico">
						<GoogleChart anos={value} valor={prestacaoChart} />
					</div>
				</div>
				<div className="box-campos">
					<a
						href="http://localhost:8080/api-finoban/download/csv"
						className="botao"
					>
						Salvar e Baixar CSV
					</a>
					<button
						className="btn-contratar"
						onClick={() => {
							setModalShowContratar(true);
						}}
					>
						Gostei, quero contratar
					</button>
				</div>
			</div>

			<div className="alert-avaliacao">
				<img src={BtnClose} className="btn-close" />
				<h1 className="titulo-avaliacao">Avalie nosso serviço</h1>
				<div className="btn-avaliacao">
					<img
						src={Like}
						alt=""
						onClick={() => setModalShowFeedback(true)}
					/>
					<img src={Deslike} alt="" onClick={avaliacaoDeslike} />
				</div>
			</div>

			<ModalFeedback
				show={modalShowFeedback}
				onHide={() => setModalShowFeedback(false)}
			/>

			<ModalContratar
				show={modalShowContratar}
				onHide={() => setModalShowContratar(false)}
			/>

			<Footer />
		</>
	);
}

export default Dashboard;

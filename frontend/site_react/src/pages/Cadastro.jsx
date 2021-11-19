import axios from "axios";
import React, { useState } from "react";
import { useHistory } from "react-router";
import Footer from "../components/Footer";
import Header from "../components/Header";
import api from "../services/api";
import CpfCnpj from "@react-br-forms/cpf-cnpj-mask";
import { Link } from "react-router-dom";
import UseForm from "../components/UseForm";
import validate from "../components/ValidacaoFormCadastro";
import { toast } from "react-toastify";
import ModalAviso from "../components/Toastify";
import LoadingScreen from "../components/LoadingScreenSemHeader";
import respostaEnum from "../utils/respostaEnum";

const Form = () => {
	const history = useHistory();
	const [resposta, setResposta] = useState(respostaEnum.NAO_REQUISITADO);
	const [cep, setCep] = useState("");
	const [dadosCep, setDadosCep] = useState({});
	const [bairro, setBairro] = useState("");
	const [endereco, setEndereco] = useState("");
	const [numero, setNumero] = useState("");
	const [dataNasc, setDataNasc] = useState("");
	const { values, errors, handleChange, handleSubmit } = UseForm(
		efetuarCadastro,
		validate
	);

	const handler = (event) => {
		if (event.key == "Enter") {
			if (validate.errors) {
				efetuarCadastro();
			}
		}
	};

	async function validarCep(event) {
		if (event.target.value.length >= 8) {
			const cepDigitado = event.target.value;
			cepDigitado.includes("-") ? cepDigitado.replace("-", "") : cepDigitado;
			setCep(cepDigitado);

			try {
				const response = await api.get(`https://viacep.com.br/ws/${cepDigitado}/json/`);
				setDadosCep(response.data);
				setBairro(response.data.bairro);
				setEndereco(`${response.data.logradouro} ${numero ? `, ${numero}` : ""}, ${bairro}, ${response.data.localidade} - ${response.data.uf}`);
			} catch {
				toast.error("Ocorreu um erro ao verificar o seu CEP!");
			}
		}
	}

	async function efetuarCadastro() {
		const data = {
			nome: values.nome,
			cpf: values.cpf,
			email: values.email,
			senha: values.senha,
			cep: cep,
			numero: numero,
			bairro: bairro,
			dataNasc: dataNasc,
		};

		try {
			setResposta(respostaEnum.ESPERANDO);
			await api.post("/cadastro", data);
			toast.success("Cadastro realizado com sucesso...");
			setTimeout(() => {
				setResposta(respostaEnum.SUCESSO);
				history.push({
					pathname: "/login"
				});
			}, 3000);
		} catch {
			setResposta(respostaEnum.ERROR);
			toast.error("Ocorreu um erro ao realizar o cadastro!");
		}
	}

	if (resposta === respostaEnum.ESPERANDO) {
		return <LoadingScreen />;
	}

	return (
		<>
			<div className="form-title">
				<h2>Cadastro</h2>
			</div>

			<form onSubmit={handleSubmit} novalidate>
				<div className="form-holder">
					<div className="input-holder">
						<h4>Nome completo:</h4>
						<input
							type="text"
							name="nome"
							id="nome_completo_cadastro"
							className={`input ${errors.nome && "is-danger"}`}
							onChange={handleChange}
							value={values.nome || ""}
							onKeyPress={(e) => handler(e)}
						/>
						{errors.nome && (
							<p className="text-danger mt-3">{errors.nome}</p>
						)}
					</div>

					<div className="input-holder">
						<h4>CPF:</h4>
						<CpfCnpj
							type="tel"
							id="cpf"
							name="cpf"
							maxLength="14"
							className={`input ${errors.cpf && "is-danger"}`}
							onChange={handleChange}
							value={values.cpf || ""}
							onKeyPress={(e) => handler(e)}
						/>
						{errors.cpf && (
							<p className="text-danger mt-3">{errors.cpf}</p>
						)}
					</div>

					<div className="input-holder">
						<h4>E-mail:</h4>
						<input
							type="text"
							name="email"
							id="nome_cadastro"
							className={`input ${errors.email && "is-danger"}`}
							onChange={handleChange}
							value={values.email || ""}
							onKeyPress={(e) => handler(e)}
						/>
						{errors.email && (
							<p className="text-danger mt-3">{errors.email}</p>
						)}
					</div>

					<div className="input-holder">
						<div className="input-holder-description">
							<h4>Senha:</h4>
							<p>Conter no mínimo 6 caracteres</p>
						</div>
						<input
							type="password"
							name="senha"
							id="login_cadastro"
							className={`input ${errors.senha && "is-danger"}`}
							onChange={handleChange}
							value={values.senha || ""}
							onKeyPress={(e) => handler(e)}
						/>
						{errors.senha && (
							<p className="text-danger mt-3">{errors.senha}</p>
						)}
					</div>

					<div className="input-holder">
						<h4>Confirmar Senha:</h4>
						<input
							type="password"
							name="confirmarSenha"
							id="login_cadastro"
							className={`input ${
								errors.confirmarSenha && "is-danger"
							}`}
							onChange={handleChange}
							defaultValue={values.confirmarSenha || ""}
							onKeyPress={(e) => handler(e)}
						/>
						{errors.confirmarSenha && (
							<p className="text-danger mt-3">
								{errors.confirmarSenha}
							</p>
						)}
					</div>

					<div className="double-input-holder">
						<div className="single-input-holder">
							<h4>CEP:</h4>
							<input
								type="text"
								name="cep"
								id="cep_cadastro"
								onChange={validarCep}
								maxLength="9"
							/>
						</div>
						<div className="single-input-holder">
							<h4>Número:</h4>
							<input
								type="number"
								name="numeroCasa"
								id="numero_cadastro"
								onChange={(e) => {
									const numeroAtual = e.target.value;
									setNumero(numeroAtual);
									endereco &&
										setEndereco(
											`${dadosCep.logradouro} ${
												numero ? `${numeroAtual}` : ""
											}, ${dadosCep.bairro}, ${
												dadosCep.localidade
											} - ${dadosCep.uf}`
										);
								}}
							/>
						</div>
					</div>

					<div className="input-holder">
						<h4>Endereço:</h4>
						<input
							className="input-disabled"
							type="text"
							name="endereco"
							id="endereco_cadastro"
							value={endereco}
							disabled
						/>
					</div>

					<div className="input-holder">
						<h4>Data de Nascimento:</h4>
						<input
							type="date"
							name="datanasc"
							id="data_nascimento_cadastro"
							onChange={(e) => setDataNasc(e.target.value)}
						/>
					</div>

					<div className="form-subtext-holder mt-5">
						<p className="fonte-16">Já possui uma conta?</p>
						<Link id="possui-conta-login" to="/login">
							Clique aqui para fazer login.
						</Link>
					</div>

					<div className="button-holder-sign-in-up">
						<button id="bnt-cadastro" type="submit">
							Cadastrar
						</button>
					</div>
				</div>
			</form>
		</>
	);
};

export default function Cadastro() {
	return (
		<>
			<Header />
			<ModalAviso />
			<Form />
			<Footer />
		</>
	);
}

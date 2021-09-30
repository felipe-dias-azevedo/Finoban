import axios from "axios";
import React, { useState } from "react";
import { useHistory } from "react-router";
import api from "../services/api";
import { Link } from "react-router-dom";
import LogoFinobanLight from "../assets/images/logo-finoban-light.svg";
import ImgEsqueciMinhaSenha from "../assets/images/imagem-esqueci-minha-senha.svg";
import ModalAviso from "../components/Toastify";
import respostaEnum from "../utils/respostaEnum";
import LoadingScreen from "../components/LoadingScreenSemHeader";
import configurarToast from "../utils/toastService";
import { toast } from "react-toastify";
import UseForm from "../components/UseForm";
import validate from "../components/ValidacaoFormEsqueciSenha";

const Form = () => {
	const [resposta, setResposta] = useState(respostaEnum.NAO_REQUISITADO);

	const handler = (event) => {
		if (event.key == "Enter") {
			if (validate.errors) {
				efetuarRedefinicao();
			}
		}
	};

	const { values, errors, handleChange, handleSubmit } = UseForm(
		efetuarRedefinicao,
		validate
	);

	function efetuarRedefinicao() {
		const data = {
			email: values.email,
		};

		setResposta(respostaEnum.ESPERANDO);

		api.post("/usuarios/iniciar-redefinicao-senha", data)
			.then(() => {
				setResposta(respostaEnum.SUCESSO);
				toast.success("Enviamos uma mensagem para seu e-mail");
			})
			.catch((e) => {
				let status = e.response.status;
				setResposta(respostaEnum.ERROR);
				if (status === 404) {
					toast.warning("Não encontramos seu e-mail");
				} else if (status === 500) {
					toast.error("Falha ao redefinir senha.");
				}
			});
	}

	if (resposta === respostaEnum.ESPERANDO) {
		return <LoadingScreen />;
	}

	return (
		<>
			<div className="div-corpo">
				<div className="retangulo-pai">
					<div className="retangulo-duas-partes">
						<div className="parte-esquerda">
							<img
								id="img-esqueci-minha-senha"
								src={ImgEsqueciMinhaSenha}
							/>
						</div>
						<div className="parte-direita">
							<img
								className="logo"
								src={LogoFinobanLight}
								alt="Logo Finoban"
							/>
							<h1 id="h1-esqueceu-sua-senha">
								Esqueceu sua senha?
							</h1>

							<form onSubmit={handleSubmit} novalidate>
								<input
									autoComplete="off"
									placeholder="E-mail"
									className={`input ${
										errors.email && "is-danger"
									}`}
									type="text"
									name="email"
									onChange={handleChange}
									value={values.email || ""}
									onKeyPress={(e) => handler(e)}
								/>
								{errors.email && (
									<p className="text-danger">
										{errors.email}
									</p>
								)}

								<div className="button-link-voltar">
									<button
										type="submit"
										className="bg-transparente cor-verde"
									>
										<a href="/login">Voltar ao Login</a>
									</button>
									<button type="submit" className="ml-3">
										<a>Redefinir senha</a>
									</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</>
	);
};

export default function EsqueciMinhaSenha() {
	return (
		<>
			<ModalAviso />
			<Form />
		</>
	);
}

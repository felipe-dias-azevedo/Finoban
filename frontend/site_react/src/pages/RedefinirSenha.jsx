import axios from "axios";
import React, { useState } from "react";
import { useEffect } from "react";
import { useHistory, useParams } from "react-router";
import api from "../services/api";
import { Link } from "react-router-dom";
import LogoFinobanLight from "../assets/images/logo-finoban-light.svg";
import ImgEsqueciMinhaSenha from "../assets/images/imagem-esqueci-minha-senha.svg";
import ModalAviso from "../components/Toastify";
import respostaEnum from "../utils/respostaEnum";
import LoadingScreen from "../components/LoadingScreen";
import configurarToast from "../utils/toastService";
import { toast } from "react-toastify";
import PaginaNaoExiste from "../pages/PaginaNaoExiste";
import UseForm from "../components/UseForm";
import validate from "../components/ValidacaoFormEsqueciSenha";

const Form = () => {
	const history = useHistory();
	const { jwt } = useParams();
	const [jwtIsValid, setJwtIsValid] = useState(false);
	const [emailRetornoRequisicao, setEmailRetornoRequisicao] = useState("");
	const [novaSenha, setNovaSenha] = useState("");
	const [confirmacaoNovaSenha, setConfirmacaoNovaSenha] = useState("");

	useEffect(() => {
		try {
			const response = api.get(`/usuarios/redefinir-senha/verificar/${jwt}`);
			setJwtIsValid(true);
			setEmailRetornoRequisicao(response.data.data.email);
		} catch {
			const status = e.response.data.code;
		}
	}, []);

	if (!jwtIsValid)
		return (
			<>
				<PaginaNaoExiste />
			</>
		);

	const RedefinirSenha = (e) => {
		e.preventDefault();

		if (novaSenha != confirmacaoNovaSenha) toast.error("Senhas diferentes");

		const data = {
			email: emailRetornoRequisicao,
			tokenJwt: jwt,
			novaSenha: novaSenha,
		};

		try {
			const response = api.post("/usuarios/redefinir-senha", data);
			toast.success("Senha alterada com sucesso");
			history.push({
				pathname: "/login",
			});
		} catch {
			toast.error("Ocorreu um erro ao redefinir a sua senha!");
		}
	};

	return (
		<main>
			<div className="container">
				<div className="logo">
					<img src={LogoFinobanLight} alt="" />
				</div>
				<div className="content-form">
					<form action="" onSubmit={RedefinirSenha}>
						<br />
						<h1 className="text-center">Redefinição de senha</h1>
						<br />
						<div className="redefinir-senha">
							<h4>Insira sua senha:</h4>
							<input
								type="password"
								onChange={(e) => setNovaSenha(e.target.value)}
							/>
							<h4 className="mt-4">Repita sua senha:</h4>
							<input
								type="password"
								onChange={(e) =>
									setConfirmacaoNovaSenha(e.target.value)
								}
							/>

							<div className="d-flex flex-row justify-content-beetwen mt-5 fonte-15">
								<button
									type="submit"
									className="bg-transparente cor-verde mr-15"
								>
									<Link to="/login">Voltar ao login</Link>
								</button>
								<button type="submit">Redefinir senha</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</main>
	);
};

export default function RedefinirSenha() {
	return (
		<>
			<ModalAviso />
			<Form />
		</>
	);
}

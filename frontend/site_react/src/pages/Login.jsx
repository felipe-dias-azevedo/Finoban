import React, { useContext, useState } from "react";
import Footer from "../components/Footer";
import Header from "../components/Header";
import api from "../services/api";
import { useHistory } from "react-router";
import { Link } from "react-router-dom";
import UseForm from "../components/UseForm";
import validate from "../components/ValidacaoFormLogin";
import configurarToast from "../utils/toastService";
import { toast } from "react-toastify";
import ModalAviso from "../components/Toastify";
import LoadingScreen from "../components/LoadingScreenSemHeader";
import respostaEnum from "../utils/respostaEnum";
import { UserContext, useUser } from "../contexts/UserContext";

const Form = () => {
	const history = useHistory();
	const [resposta, setResposta] = useState(respostaEnum.NAO_REQUISITADO);
	// const { setDataUsuario, setAuthorization } = useUser();
	const { setDataUsuario, setAuthorization } = useContext(UserContext);
	const { values, errors, handleChange, handleSubmit } = UseForm(
		efetuarLogin,
		validate
	);

	const handler = (event) => {
		if (event.key == "Enter") {
			if (validate.errors) {
				efetuarLogin();
			}
		}
	};

	function efetuarLogin(e) {
		const data = {
			email: values.email,
			senha: values.password,
		};

		api.post("/login", data, {})
			.then((e) => {
				setResposta(respostaEnum.ESPERANDO);
				if (e.status === 200) {
					console.log(e);
					var objUsuario = {
						id: e.data.data.id,
						nome: e.data.data.nome,
						email: e.data.data.email,
					};

					setDataUsuario(objUsuario);
					setAuthorization(e.data.data.token);

					toast.success(`Bem-vindo(a) ${objUsuario.nome}!`);
					sessionStorage.setItem("usuarioLogado", true);
					sessionStorage.setItem("dadosUsuario", objUsuario);

					<LoadingScreen />

					setTimeout(() => {
						setResposta(respostaEnum.SUCESSO);
						history.push({
							pathname: "/dashboard",
						});
					}, 5000);
				} else {
					setResposta(respostaEnum.ERROR);
					toast.warning("Usuário ou senha incorreto");
				}
			})
			.catch((e) => {
				const status = e.response.data.code;
				if (status == "FIN07") toast.error("Email não encontrado");
				if (status == "FIN08") toast.error("A senha informada é inválida");
				if (status == "FIN09") toast.error("Usuário já possui uma sessão ativa");
				setResposta(respostaEnum.ERROR);
			});
	}

	if (resposta === respostaEnum.ESPERANDO) {
		return <LoadingScreen />;
	}

	return (
		<>
			<div className="form-title">
				<h2>Login</h2>
			</div>

			<form onSubmit={handleSubmit} novalidate className="form-holder">
				<div className="input-holder">
					<h4>E-mail:</h4>
					<input
						autoComplete="off"
						className={`input ${errors.email && "is-danger"}`}
						type="text"
						name="email"
						onChange={handleChange}
						value={values.email || ""}
						onKeyPress={(e) => handler(e)}
					/>
					{errors.email && (
						<p className="text-danger mt-3">{errors.email}</p>
					)}
				</div>
				<div className="input-holder">
					<h4>Senha:</h4>
					<input
						className={`input ${errors.password && "is-danger"}`}
						type="password"
						name="password"
						onChange={handleChange}
						value={values.password || ""}
						onKeyPress={(e) => handler(e)}
					/>
					{errors.password && (
						<p className="text-danger mt-3">{errors.password}</p>
					)}
				</div>

				<div className="form-subtext-holder">
					<p>Não possui uma conta?</p>
					<Link to="/cadastro" className="font-weight-bold">
						Clique aqui para se cadastrar.
					</Link>
				</div>

				<div className="form-subtext-holder">
					<p>Esqueceu a senha?</p>
					<Link
						to="/esqueci-minha-senha"
						className="font-weight-bold"
					>
						Clique aqui
					</Link>
				</div>

				<div className="button-holder-sign-in-up">
					<button type="submit">Entrar</button>
				</div>
			</form>
		</>
	);
};

export default function Login() {
	return (
		<>
			<Header />
			<ModalAviso />
			<Form />
			<Footer />
		</>
	);
}

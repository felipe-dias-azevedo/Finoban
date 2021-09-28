import React, { useState } from "react";
import Footer from "../components/Footer";
import Header from "../components/Header";
import api from "../services/api";
import { useHistory } from "react-router";
import Modal from "react-bootstrap/Modal";
import { Link } from "react-router-dom";
import { Alert, AlertTitle } from "@material-ui/lab";
import UseForm from "../components/UseForm";
import validate from "../components/ValidacaoForm";

var nomeUsuario;

function ModalLoginSucesso(props) {
	return (
		<Modal
			{...props}
			size="lg"
			aria-labelledby="contained-modal-title-vcenter"
			centered
		>
			<Modal.Header closeButton>
				<Modal.Title id="text-center">
					Bem-vindo(a) {nomeUsuario == null ? "" : nomeUsuario}...
				</Modal.Title>
			</Modal.Header>
		</Modal>
	);
}

function ModalLoginErro(props) {
	return (
		<Modal
			{...props}
			size="lg"
			aria-labelledby="contained-modal-title-vcenter"
			centered
		>
			<Modal.Header closeButton>
				<Modal.Title id="text-center">
					Email ou senha inválidos!
				</Modal.Title>
			</Modal.Header>
		</Modal>
	);
}

const Form = () => {
	const history = useHistory();
	const [modalLoginShow, setModalLoginShow] = React.useState(false);
	const [modalLoginErroShow, setModalLoginErroShow] = React.useState(false);
	const [erroLogin, setErroLogin] = useState(false);
	const [erroMsgm, setErroMsgm] = useState("");
	const [email, setEmail] = useState("");
	const [senha, setSenha] = useState("");
	const { values, errors, handleChange, handleSubmit } = UseForm(
		efetuarLogin,
		validate
	);

	function efetuarLogin(e) {
		const data = {
			email: values.email,
			senha: values.password,
		};

		api.post("/login", data, {})
			.then((e) => {
				if (e.status === 200) {
					var objUsuario = {
						id: e.data.data.id,
						nome: e.data.data.nome,
						email: e.data.data.email,
					};

					nomeUsuario = objUsuario.nome;
					sessionStorage.setItem("dadosUsuario", objUsuario);

					setModalLoginShow(true);
					setTimeout(() => {
						setModalLoginShow(false);
						history.push({
							pathname: "/dashboard",
						});
					}, 3000);
				} else {
					setErroLogin(true);
					setErroMsgm("Usuário ou senha incorreto");
					console.error(e);
				}
			})
			.catch((e) => {
				setErroLogin(true);
				setErroMsgm("Erro ao realizar login");
				console.error(e);
			});
	}

	const erroStyle = {
		display: "flex",
		alingItens: "center",
		justifyContent: "center",
		marginTop: "5%",
	};

	return (
		<>
			<ModalLoginSucesso
				show={modalLoginShow}
				onHide={() => setModalLoginShow(false)}
			/>

			<ModalLoginErro
				show={modalLoginErroShow}
				onHide={() => setModalLoginErroShow(false)}
			/>

			<div className="form-title">
				<h2>Login</h2>
			</div>

			{erroLogin && (
				<div style={erroStyle}>
					<br />
					<Alert severity="error">
						<AlertTitle>
							<strong>Erro</strong>
						</AlertTitle>
						{erroMsgm}
					</Alert>
				</div>
			)}

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
						required
					/>
					{errors.email && (
						<p className="help is-danger">{errors.email}</p>
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
						required
					/>
					{errors.password && (
						<p className="help is-danger">{errors.password}</p>
					)}
				</div>

				<div className="form-subtext-holder">
					<p>Não possui uma conta?</p>
					<Link to="/cadastro">Clique aqui para se cadastrar.</Link>
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
			<Form />
			<Footer />
		</>
	);
}

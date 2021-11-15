import React, { useContext, useEffect } from "react";
import { Link, useHistory } from "react-router-dom";
import { HiSun } from "react-icons/hi";
import { HiMoon } from "react-icons/hi";
import LogoFinobanLight from "../assets/images/logo-finoban-light.svg";
import LogoFinobanDark from "../assets/images/logo-finoban-dark.svg";
import { DarkModeContext } from "../contexts/DarkModeContext";
import Breadcrumbs from "./Breadcrumbs";
import Api from "../services/api";
import configurarToast from "../utils/toastService";
import { toast } from "react-toastify";
import ModalAviso from "../components/Toastify";
import Dropdown from "../components/Dropdown";

function Header() {
	const history = useHistory();
	const { isDarkEnable, changeDarkModeState } = useContext(DarkModeContext);
	const usuarioLogado = sessionStorage.getItem("usuarioLogado");
	const dadosUsuarioLogado = JSON.parse(
		sessionStorage.getItem("dadosUsuario")
	);
	console.log(usuarioLogado);

	function efetuarLogoff() {
		var req = {};
		req.email = dadosUsuarioLogado.email;

		Api.post("/logoff", req, {})
			.then(() => {
				sessionStorage.setItem("usuarioLogado", false);
				history.push({
					pathname: "/",
					state: { logoff: true },
				});
			})
			.catch(() => {
				toast.error("Ocorreu um erro. Tente novamente mais tarde.");
			});
	}

	return (
		<header>
			<div className="topheader">
				<div className="topheader-logo">
					<Link to="/">
						{isDarkEnable ? (
							<img
								className="logo"
								src={LogoFinobanDark}
								alt="Logo Finoban"
							/>
						) : (
							<img
								className="logo"
								src={LogoFinobanLight}
								alt="Logo Finoban"
							/>
						)}
					</Link>
				</div>
				<div className="links">
					<ul>
						<li>
							<Link id="analise" to="/analise">ANÁLISES DE NEGÓCIO</Link>
						</li>
						<li>
							<Link id="sobrenos" to="/sobrenos">SOBRE</Link>
						</li>
						<li>
							<Link id="openbanking" to="/openbanking">OPENBANKING</Link>
						</li>
						<li>
							<Dropdown title="TESTES INTERNOS">
								<Link id="testes" to="/testes">GERAL</Link>
								<Link id="testes-analytics" to="/testes/analytics">ANALYTICS</Link>
							</Dropdown>
						</li>
					</ul>
				</div>
				<div className="topheader-options">
					<button
					
						className="btn-topheader"
						onClick={() => history.push("/cadastro")}
					>
						Cadastro
					</button>

					{usuarioLogado == "true" && (
						<button
							className="btn-topheader"
							onClick={efetuarLogoff}
						>
							Logout
						</button>
					)}

					{usuarioLogado == "false" && (
						<button
							className="btn-topheader"
							onClick={() => history.push("/login")}
						>
							Login
						</button>
					)}

					{usuarioLogado == null && (
						<button
							className="btn-topheader"
							onClick={() => history.push("/login")}
						>
							Login
						</button>
					)}

					<div className="div-logo-dark-mode">
						<div className="sun sun-logo">
							<HiSun
								className="sun-icon"
								onClick={changeDarkModeState}>
							</HiSun>
						</div>

						<div className="moon moon-logo">
							<HiMoon
								className="moon-icon"
								onClick={changeDarkModeState}>
							</HiMoon>
						</div>

					</div>
				</div>
			</div>
			<div className="subheader shadow-header">
				<Breadcrumbs />
			</div>
			<ModalAviso />
		</header>
	);
}

export default Header;

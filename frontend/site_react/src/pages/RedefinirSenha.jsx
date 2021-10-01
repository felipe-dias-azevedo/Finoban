import axios from "axios";
import React, { useState } from "react";
import { useHistory } from "react-router";
import api from "../services/api";
import { Link } from "react-router-dom";
import LogoFinobanLight from "../assets/images/logo-finoban-light.svg";
import ImgEsqueciMinhaSenha from "../assets/images/imagem-esqueci-minha-senha.svg";
import ModalAviso from "../components/Toastify";
import respostaEnum from "../utils/respostaEnum";
import LoadingScreen from "../components/LoadingScreen";
import configurarToast from "../utils/toastService";
import { toast } from "react-toastify";
import UseForm from "../components/UseForm";
import validate from "../components/ValidacaoFormEsqueciSenha";

export default function RedefinirSenha() {
	return (
		<main>
			<div className="container">
				<div className="logo">
					<img src={LogoFinobanLight} alt="" />
				</div>
				<div className="content-form">
					<form action="">
						<br />
						<h1 className="text-center">Redefinição de senha</h1>
						<br />
						<div className="redefinir-senha">
							<h4>Insira sua senha:</h4>
							<input type="password" />
							<h4 className="mt-4">Repita sua senha:</h4>
							<input type="password" />

                            <div className="d-flex flex-row justify-content-beetwen mt-5 fonte-15">
							<button
								type="submit"
								className="bg-transparente cor-verde mr-15"
							>
								<Link to="/login">Voltar ao login</Link>
							</button>
							<button type="submit">
								Redefinir senha
							</button>
                            </div>
						</div>
					</form>
				</div>
			</div>
		</main>
	);
}

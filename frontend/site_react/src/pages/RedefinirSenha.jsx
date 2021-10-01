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
                        <h1>Redefinição de senha</h1>
                        <br />
                        <div className="redefinir-senha">
                            <h4>Insira sua senha:</h4>
                            <input type="passoword" />
                        </div>
                        <div className="redefinir-senha">
                            <h4>Repita sua senha:</h4>
                            <input type="passoword" />
                        </div>
                        <button type="submit">Redefinir senha</button>
                    </form>
                </div>
            </div>
        </main>
    );
}

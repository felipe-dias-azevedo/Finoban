import axios from "axios";
import React, { useState } from "react";
import { useHistory } from "react-router";
import api from "../services/api";
import { Link } from "react-router-dom";
import LogoFinobanLight from '../assets/images/logo-finoban-light.svg';
import ImgEsqueciMinhaSenha from '../assets/images/imagem-esqueci-minha-senha.svg';
import ModalAviso from '../components/Toastify';
import respostaEnum from '../utils/respostaEnum';
import LoadingScreen from '../components/LoadingScreen';
import configurarToast from '../utils/toastService';
import { toast } from 'react-toastify';


export default function EsqueciMinhaSenha() {
    
    const [email, setEmail] = useState("");
    const [resposta, setResposta] = useState(respostaEnum.NAO_REQUISITADO);

    const data = {
        email: email,
    };


    function validarLogin(e) {
        if (email.trim() == "") {
            toast.error("Campo email não pode ser vazio");
        }

        e.preventDefault();
        setResposta(respostaEnum.ESPERANDO);

        api.post("/usuarios/iniciar-redefinicao-senha", data).then(e => {
            setResposta(respostaEnum.SUCESSO);
            toast.success("Enviamos uma mensagem para seu e-mail");
        }).catch(e => {
            let status = e.response.status;
            setResposta(respostaEnum.ERROR);
            if (status === 404) {
                toast.warning("Não encontramos seu e-mail");
            } else if (status === 500) {
                toast.error("Falha ao redefinir senha.");
            }
        })
    }

    if (resposta === respostaEnum.ESPERANDO) {
        return (
            <LoadingScreen />
        );
    }

    return (
        <>
            <ModalAviso />
            <div className="div-corpo">
                <div className="retangulo-pai">
                    <div className="retangulo-duas-partes">
                        <div className="parte-esquerda">
                            <img id="img-esqueci-minha-senha" src={ImgEsqueciMinhaSenha} />
                        </div>
                        <div className="parte-direita">
                            <img className="logo" src={LogoFinobanLight} alt="Logo Finoban" />
                            <h1 id="h1-esqueceu-sua-senha">Esqueceu sua senha?</h1>
                            <form onSubmit={validarLogin}>
                                <input
                                    type="email"
                                    placeholder="Insira seu e-mail"
                                    onChange={(e) => setEmail(e.target.value)}
                                />
                                <div className="button-link-voltar">
                                    <button type="submit" >Redefinir senha</button>
                                    <Link to="/login">
                                        Voltar para o login.
                                    </Link>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
}

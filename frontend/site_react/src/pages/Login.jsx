import React, { useState } from 'react';
import Footer from '../components/Footer';
import Header from '../components/Header';
import api from '../services/api';
import { useHistory } from 'react-router';
import '../assets/css/login.css';

export default function Login() {

    const history = useHistory();

    const [email, setEmail] = useState("");
    const [senha, setSenha] = useState("");

    const [errorLogin, setErrorLogin] = useState(false)

    async function validarLogin(e) {
        e.preventDefault();

        let dataSimulacao = window.history.state.state.data;
        console.log(dataSimulacao);

        let respostaSimulacao;
        if (email.trim() === "" || senha.trim() === "") {
            return;
        }

        const data = {
            email,
            senha,
        }

        console.log(data);

        await api.post('/financiamento', dataSimulacao, {
            'Content-Type': 'application/json',
            'Access-Control-Allow-Origin': '*',
            'Access-Control-Allow-Headers': 'Origin, X-Requested-With, Content-Type, Accept',
            'Accept': 'application/json'
        }).then(e => {
            console.log(e.data)
            respostaSimulacao = e.data;
            console.log(respostaSimulacao);
        }).catch(e => {
            console.error(e)
        });

        await api.post('/login', data, {
            'Content-Type': 'application/json',
            'Access-Control-Allow-Origin': '*',
            'Access-Control-Allow-Headers': 'Origin, X-Requested-With, Content-Type, Accept'
        }).then(e => {
            console.log(e.data);
            console.log(e.status);
            if (e.status === 200) {
                history.push({
                    pathname: '/simulador',
                    state: { data: respostaSimulacao }
                });
            } else {
                setErrorLogin(true);
            }
        }).catch(e => {
            console.error(e);
        });


    }

    return (
        <>
            <Header />

            <div className="login">
                <h3>Login</h3>
            </div>

            <form onSubmit={validarLogin} className="entrar">
            <h3>E-mail:</h3>
                <input
                    type="email"
                    name="email"
                    onChange={(e) => setEmail(e.target.value)}
                />
                <h3>Senha:</h3>
                <input
                    type="password"
                    name="senha"
                    onChange={(e) => setSenha(e.target.value)}
                />
                <button className="bt" type="submit">
                    Entrar
                    </button>
            </form>
            <Footer />
        </>
    );
}
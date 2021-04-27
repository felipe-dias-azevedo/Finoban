import React, { useState } from 'react';
import Footer from '../components/Footer';
import Header from '../components/Header';
import api from '../services/api';
import '../assets/css/login.css';
import { useHistory } from 'react-router';

export default function Login() {
    const history = useHistory();

    const [email, setEmail] = useState("");
    const [senha, setSenha] = useState("");

    const [errorLogin, setErrorLogin] = useState(false);

    function validarLogin() {
        
        if (email.trim() === "" || senha.trim() === "") {
            return;
        }

        const data = {
            email,
            senha,
        }

        console.log(data);

        api.post('/login', data, {
            'Content-Type': 'application/json',
            'Access-Control-Allow-Origin': '*',
            'Access-Control-Allow-Headers': 'Origin, X-Requested-With, Content-Type, Accept'
        }).then(e => {
            console.log(e.data);
            if (e.status === 200) {
                history.push('/simulador');
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

            <div className="entrar">
                
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
                
                <button className="bt" onClick={validarLogin}>
                    Entrar
                </button>

            </div>

            <Footer />
    </>
    );
}
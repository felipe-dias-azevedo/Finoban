import { useState } from "react";
import { useHistory } from "react-router";
import Header from "../../components/Header";
import Footer from "../../components/Footer";
import api from '../../services/api';

function Login() {
    const history = useHistory();

    const [email, setEmail] = useState("");
    const [senha, setSenha] = useState("");

    const [styles, setStyles] = useState({
        border: "2px solid #454545",
    });

    function validarLogin() {

        if (email.trim() === "" || senha.trim() === "") {
            return;
        }

        const data = {
            email,
            senha
        }

        api.post('/login', data, {
            'Content-Type': 'application/json',
            'Access-Control-Allow-Origin': '*',
            'Access-Control-Allow-Headers': 'Origin, X-Requested-With, Content-Type, Accept'
        }).then(e => {
            if (e.status === 200) {
                history.push('/dashboard');
            } else {
                setStyles({
                    border: "4px solid red",
                });
            }
        }).catch(e => {
            console.error(e);
            setStyles({
                border: "4px solid red",
            });
        });
    }

    return (
        <>
            <Header />
            <div className="form-title">
                <h2>Análise de Dados do Negócio</h2>
            </div>

            <div className="form-holder">
                <div className="input-holder">
                    <h4>E-mail:</h4>
                    <input
                        style={ styles }
                        type="email"
                        name="email"
                        onChange={(e) => setEmail(e.target.value)}
                    />
                </div>
                <div className="input-holder">
                    <h4>Senha:</h4>
                    <input
                        style={ styles }
                        type="password"
                        name="senha"
                        onChange={(e) => setSenha(e.target.value)}
                    />
                </div>
                <div className="button-holder">
                    <button onClick={validarLogin}>
                        Entrar
                    </button>
                </div>
            </div>
            <Footer />
        </>
    )
}

export default Login;
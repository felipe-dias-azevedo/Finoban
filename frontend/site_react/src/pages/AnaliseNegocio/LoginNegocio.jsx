import { useState } from "react";
import { useHistory } from "react-router";
import Header from "../../components/Header";
import Footer from "../../components/Footer";
import { Alert, AlertTitle } from '@material-ui/lab';
import api from '../../services/api';

function Login() {
    const history = useHistory();

    const [email, setEmail] = useState("");
    const [senha, setSenha] = useState("");

    const [erroLogin, setErroLogin] = useState(false);
    const [erroMsgm, setErroMsgm] = useState("");

    const erroStyle = {
        display: "flex",
        alingItens: "center",
        justifyContent: "center"
    }

    function validarLogin() {

        if (email.trim() === "" || senha.trim() === "") {
            setErroLogin(true);
            return;
        }

        setErroLogin(false);

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
                localStorage.setItem("nomeUsuario", e.data.data.nome);
                localStorage.setItem("idUsuario", e.data.data.id);
                localStorage.setItem("emailUsuario", e.data.data.email);
                sessionStorage.setItem("tokenAuth", e.data.data.token);
                history.push('/analise/dashboard');
            } else {
                setErroLogin(true);
                setErroMsgm("Usuário ou senha incorreto");
            }
        }).catch(e => {
            console.error(e);
            setErroLogin(true);
            setErroMsgm("Erro ao realizar login");
        });
    }

    return (
        <>
            <Header />
            <div className="form-title">
                <h2>Login Análise</h2>
                <h5>Dados de Negócio</h5>
            </div>

            {erroLogin && (
                <div style={erroStyle}>
                    <Alert severity="error">
                        <AlertTitle><strong>Erro</strong></AlertTitle>
                        {erroMsgm}
                    </Alert>
                </div>
            )}

            <div className="form-holder">
                <div className="input-holder">
                    <h4>E-mail:</h4>
                    <input
                        type="email"
                        name="email"
                        onChange={(e) => setEmail(e.target.value)}
                    />
                </div>
                <div className="input-holder">
                    <h4>Senha:</h4>
                    <input
                        type="password"
                        name="senha"
                        onChange={(e) => setSenha(e.target.value)}
                    />
                </div>
                <div className="button-holder-sign-in-up">
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
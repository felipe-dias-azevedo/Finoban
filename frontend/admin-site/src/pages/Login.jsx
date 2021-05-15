import { useState } from "react";
import { useHistory } from "react-router";
import api from '../services/api';

function Login() {
  const history = useHistory();

  const [email, setEmail] = useState("");
  const [senha, setSenha] = useState("");

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
      }
    }).catch(e => {
      console.error(e);
    });
  }

  return (
    <>
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
    </>
  )
}

export default Login;
import React, { useState } from 'react';
import Footer from '../components/Footer';
import Header from '../components/Header';
import api from '../services/api';
import { useHistory } from 'react-router';
import Modal from 'react-bootstrap/Modal'
import { Link } from 'react-router-dom';
import { Alert, AlertTitle } from '@material-ui/lab';

function ModalLoginSucesso(props) {

  var nomeUsuario = localStorage.getItem("nomeUsuario");
  var idUsuario = localStorage.getItem("idUsuario");

  return (
    <Modal
      {...props}
      size="lg"
      aria-labelledby="contained-modal-title-vcenter"
      centered
    >
      <Modal.Header closeButton>
        <Modal.Title id="text-center">
          Bem-vindo(a) {nomeUsuario}...
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

export default function Login() {

  const history = useHistory();

  const [email, setEmail] = useState("");
  const [senha, setSenha] = useState("");

  const [modalLoginShow, setModalLoginShow] = React.useState(false);
  const [modalLoginErroShow, setModalLoginErroShow] = React.useState(false);

  const [erroLogin, setErroLogin] = useState(false);
  const [erroMsgm, setErroMsgm] = useState("");

  const erroStyle = {
    display: "flex",
    alingItens: "center",
    justifyContent: "center"
  }

  var dadosRecebido = localStorage.getItem("testeChave");

  let dataSimulacao = JSON.parse(dadosRecebido);

  function validarLogin(e) {
    e.preventDefault();

    let respostaSimulacao;
    if (email.trim() === "" || senha.trim() === "") {
      return;
    }

    const data = {
      email,
      senha,
    }

    api.post('/login', data, {

    }).then(e => {
      if (e.status === 200) {
        var nomeUsuarioLogado = localStorage.setItem("nomeUsuario", e.data.data.nome);
        var idUsuarioLogado = localStorage.setItem("idUsuario", e.data.data.id);
        var emailUsuarioLogado = localStorage.setItem("emailUsuario", e.data.data.email);
        // setModalLoginShow(true);
        // setTimeout(() => {
        //   setModalLoginShow(false);
        //   history.push({
        //     pathname: '/dashboard'
        //   });
        // }, 3000);
        history.push({
          pathname: '/dashboard'
        });
      } else {
        // setModalLoginErroShow(true);
        // setTimeout(() => {
        //   setModalLoginErroShow(false);
        // }, 3000);
        setErroLogin(true);
        setErroMsgm("Usuário ou senha incorreto");
        console.error(e);
      }
    }).catch(e => {
      setErroLogin(true);
      setErroMsgm("Erro ao realizar login");
      console.error(e);
    });

  }

  return (
    <>
      <Header />

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
          <Alert severity="error">
            <AlertTitle><strong>Erro</strong></AlertTitle>
            {erroMsgm}
          </Alert>
        </div>
      )}

      <form onSubmit={validarLogin} className="form-holder">
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

        <div className="form-subtext-holder">
          <p>
            Não possui uma conta?
          </p>
          <Link to="/cadastro">
            Clique aqui para se cadastrar.
          </Link>
        </div>

        <div className="button-holder-sign-in-up">
          <button type="submit">
            Entrar
          </button>
        </div>
      </form>

      <Footer />
    </>
  );
}
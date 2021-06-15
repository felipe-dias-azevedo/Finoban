import React, { useState } from 'react';
import Footer from '../components/Footer';
import Header from '../components/Header';
import api from '../services/api';
import { useHistory } from 'react-router';
import '../assets/css/login.css';
import Modal from 'react-bootstrap/Modal'
import { Link } from 'react-router-dom';

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

  var dadosRecebido = localStorage.getItem("testeChave");
  
  let dataSimulacao = JSON.parse(dadosRecebido);

  console.log(dataSimulacao);

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
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': '*',
      'Access-Control-Allow-Headers': 'Origin, X-Requested-With, Content-Type, Accept'
    }).then(e => {
      console.log(e.data);
      if (e.status === 200) {
        var nomeUsuarioLogado = localStorage.setItem("nomeUsuario", e.data.data.nome);
        var idUsuarioLogado = localStorage.setItem("idUsuario", e.data.data.id);
        var emailUsuarioLogado = localStorage.setItem("emailUsuario", e.data.data.email);
        console.log(e);
        setModalLoginShow(true);
        setTimeout(() => {
          setModalLoginShow(false);
          history.push({
            pathname: '/dashboard'
          });
        }, 3000);
      } else {
        setModalLoginErroShow(true);
        setTimeout(() => {
          setModalLoginErroShow(false);
        }, 3000);
      }
    }).catch(e => {
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

        <p className="txt-cadastrar">Não possui uma conta? Clique <Link to="/cadastro">aqui para se cadastrar.</Link></p>

        <button className="bt fw-500" type="submit">
          Entrar
                    </button>
      </form>

      <Footer />
    </>
  );
}
import axios from "axios";
import React, { useState } from "react";
import { useHistory } from "react-router";
import Footer from "../components/Footer";
import Header from "../components/Header";
import api from "../services/api";
import CpfCnpj from "@react-br-forms/cpf-cnpj-mask";
import { Alert, AlertTitle } from '@material-ui/lab'
import { Link } from "react-router-dom";

function validarCNPJ(cnpj) {

  cnpj = cnpj.replace(/[^\d]+/g, '');

  if (cnpj == '') return false;

  if (cnpj.length != 14)
    return false;

  // Elimina CNPJs invalidos conhecidos
  if (cnpj == "00000000000000" ||
    cnpj == "11111111111111" ||
    cnpj == "22222222222222" ||
    cnpj == "33333333333333" ||
    cnpj == "44444444444444" ||
    cnpj == "55555555555555" ||
    cnpj == "66666666666666" ||
    cnpj == "77777777777777" ||
    cnpj == "88888888888888" ||
    cnpj == "99999999999999")
    return false;

  // Valida DVs
  var tamanho, numeros, digitos, soma, pos, resultado;
  tamanho = cnpj.length - 2
  numeros = cnpj.substring(0, tamanho);
  digitos = cnpj.substring(tamanho);
  soma = 0;
  pos = tamanho - 7;
  for (var i = tamanho; i >= 1; i--) {
    soma += numeros.charAt(tamanho - i) * pos--;
    if (pos < 2)
      pos = 9;
  }
  resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
  if (resultado != digitos.charAt(0))
    return false;

  tamanho = tamanho + 1;
  numeros = cnpj.substring(0, tamanho);
  soma = 0;
  pos = tamanho - 7;
  for (var i = tamanho; i >= 1; i--) {
    soma += numeros.charAt(tamanho - i) * pos--;
    if (pos < 2)
      pos = 9;
  }
  resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
  if (resultado != digitos.charAt(1))
    return false;

  return true;

}

function validateEmail(email) {
  const re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
  return re.test(email);
}


export default function Cadastro() {
  const history = useHistory();

  const [nome, setNome] = useState("");
  const [cnpj, setCnpj] = useState("");
  const [email, setEmail] = useState("");
  const [senha, setSenha] = useState("");
  const [senhaConfirma, setSenhaConfirma] = useState("");
  const [cep, setCep] = useState("");
  const [numero, setNumero] = useState("");
  const [dataNasc, setDataNasc] = useState(undefined);
  const [mask, setMask] = useState("");
  const [erroForm, setErroForm] = useState(false);
  const [erroFormMsgm, setErroFormMsgm] = useState("");

  const [endereco, setEndereco] = useState("");
  const [dadosCep, setDadosCep] = useState({});
  const [bairro, setBairro] = useState("");

  const [erroNome, setErroNome] = useState("");
  const [erroCnpj, setErroCnpj] = useState("");
  const [erroEmail, setErroEmail] = useState("");
  const [erroSenha, setErroSenha] = useState("");
  const [erroConfirmaSenha, setErroConfirmaSenha] = useState("");
  const [erroCep, setErroCep] = useState("");
  const [erroNumero, setErroNumero] = useState("");
  const [erroDataNascimento, setErroDataNascimento] = useState("");

  const [errorLogin, setErrorLogin] = useState(false);

  function validarCep(event) {
    const cepDigitado = event.target.value;
    setCep(cepDigitado);

    if (cep.length !== 7) {
      return;
    }

    console.log(bairro);

    axios
      .get(
        `https://viacep.com.br/ws/${cepDigitado}/json/`,
        {},
        {
          "Content-Type": "application/json",
          "Access-Control-Allow-Headers":
            "Origin, X-Requested-With, Content-Type, Accept",
        }
      )
      .then((e) => {
        const dadosCepRes = e.data;
        setDadosCep(dadosCepRes);
        setBairro(dadosCep.bairro);
        console.log(bairro);
        setEndereco(
          `${dadosCepRes.logradouro} ${numero ? `, ${numero}` : ""}, ${dadosCepRes.bairro
          }, ${dadosCepRes.localidade} - ${dadosCepRes.uf}`
        );
      })
      .catch((e) => {
        console.error(e);
        setErroCep("Insira um CEP válido");
      });
  }

  function validarCadastro() {

    setErroForm(false);
    setErroNome("");
    setErroCnpj("");
    setErroEmail("");
    setErroSenha("");
    setErroConfirmaSenha("");
    setErroCep("");
    setErroNumero("");
    setErroDataNascimento("");

    if (nome && nome.trim() === "") {
      setErroNome("Insira seu nome completo");
    }
    if (cnpj && cnpj.trim() === "" || !(validarCNPJ(cnpj)) ) {
      setErroCnpj("Insira um CNPJ válido")
    }
    if (email && email.trim() === "" || !(validateEmail(email))) {
      setErroEmail("Insira um e-mail válido")
    }
    if (erroSenha && erroSenha.trim() === "") {
      setErroSenha("Insira uma senha com mais de 6 caracteres")
    }
    if (cep && cep.trim() === "") {
      setErroCep("Insira um CEP válido")
    }
    if (numero && numero.trim() === "") {
      setErroNumero("Insira um número válido")
    }
    if (dataNasc === undefined) {
      setErroDataNascimento("Insira uma data de nascimento válida")
    }
    if (senha.length < 6) {
      setErroSenha("Insira uma senha com mais de 6 caracteres");
    } else if (senha !== senhaConfirma) {
      setErroConfirmaSenha("As duas senhas precisam ser iguais");
    }

    if (erroNome !== "" || erroCnpj !== "" || erroEmail !== "" || erroSenha !== "" ||
      erroConfirmaSenha !== "" || erroCep !== "" || erroNumero !== "" || dataNasc === undefined) {
      setErroFormMsgm("Erro no formulário!");
      setErroForm(true);
    } else {
      const data = {
        nome,
        cnpj,
        email,
        senha,
        cep,
        numero,
        dataNasc,
        bairro,
      };

      console.log(data);

      api
        .post("/cadastro", data, {})
        .then((e) => {
          console.log(e.data);
          if (e.status === 201) {
            history.push("/login");
          } else {
            setErrorLogin(true);
          }
        })
        .catch((e) => {
          console.error(e);
          setErroFormMsgm("Erro ao realizar requisição");
          setErroForm(true);
        });
    }

  }

  return (
    <>
      <Header />
      <div className="form-title">
        <h2>Cadastro</h2>
      </div>

      <div className="form-holder">
        {erroForm && (
          <Alert severity="error">
            <AlertTitle><strong>Erro</strong></AlertTitle>
            {erroFormMsgm}
          </Alert>
        )}
        <div className="input-holder">
          <h4>Nome completo:</h4>
          <input
            type="text"
            name="nome"
            id="nome_completo_cadastro"
            onChange={(e) => setNome(e.target.value)}
          />
          <label className="label-erro">{erroNome}</label>
        </div>

        <div className="input-holder">
          <h4>CNPJ:</h4>
          <CpfCnpj
            type="tel"
            value={cnpj}
            onChange={(e, type) => {
              setCnpj(e.target.value);
              setMask(type === "CNPJ");
            }}
          />
          <label className="label-erro">{erroCnpj}</label>
        </div>

        <div className="input-holder">
          <h4>E-mail:</h4>
          <input
            type="text"
            name="email"
            id="nome_cadastro"
            onChange={(e) => setEmail(e.target.value)}
          />
          <label className="label-erro">{erroEmail}</label>
        </div>

        <div className="input-holder">
          <div className="input-holder-description">
            <h4>Senha:</h4>
            <p>Conter no mínimo 6 caracteres</p>
          </div>
          <input
            type="password"
            name="senha"
            id="login_cadastro"
            onChange={(e) => setSenha(e.target.value)}
          />
          <label className="label-erro">{erroSenha}</label>
        </div>

        <div className="input-holder">
          <h4>Confirmar Senha:</h4>
          <input
            type="password"
            name="confirmar_senha"
            id="confirmar_senha_cadastro"
            onChange={(e) => setSenhaConfirma(e.target.value)}
          />
          <label className="label-erro">{erroConfirmaSenha}</label>
        </div>

        <div className="double-input-holder">
          <div className="single-input-holder">
            <h4>CEP:</h4>
            <input
              type="text"
              name="cep"
              id="cep_cadastro"
              onChange={validarCep}
            />
            <label className="label-erro">{erroCep}</label>
          </div>
          <div className="single-input-holder">
            <h4>Número:</h4>
            <input
              type="text"
              name="numeroCasa"
              id="numero_cadastro"
              onChange={(e) => {
                const numeroAtual = e.target.value;
                setNumero(numeroAtual);
                endereco && setEndereco(`${dadosCep.logradouro} ${numero ? `${numeroAtual}` : ""}, ${dadosCep.bairro}, ${dadosCep.localidade} - ${dadosCep.uf}`);
              }}
            />
            <label className="label-erro">{erroNumero}</label>
          </div>
        </div>

        <div className="input-holder">
          <h4>Endereço:</h4>
          <input
            className="input-disabled"
            type="text"
            name="endereco"
            id="endereco_cadastro"
            value={endereco}
            disabled
          />
        </div>

        <div className="input-holder">
          <h4>Data de Nascimento:</h4>
          <input
            type="date"
            name="datanasc"
            id="data_nascimento_cadastro"
            onChange={(e) => setDataNasc(e.target.value)}
          />
          <label className="label-erro">{erroDataNascimento}</label>
        </div>

        <div className="form-subtext-holder">
          <p>
            Já possui uma conta?
          </p>
          <Link to="/login">
            Clique aqui para fazer login.
          </Link>
        </div>

        <div className="button-holder-sign-in-up">
          <button onClick={validarCadastro}>
            Cadastrar
          </button>
        </div>
      </div>
      <Footer />
    </>
  );
}

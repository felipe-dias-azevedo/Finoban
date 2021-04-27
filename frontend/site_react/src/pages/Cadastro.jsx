import axios from 'axios';
import React, { useState } from 'react';
import { useHistory } from 'react-router';
import Footer from '../components/Footer';
import Header from '../components/Header';
import api from '../services/api';

export default function Cadastro() {
    const history = useHistory();

    const [nome, setNome] = useState("");
    const [cnpj, setCnpj] = useState("");
    const [email, setEmail] = useState("");
    const [senha, setSenha] = useState("");
    const [senhaConfirma, setSenhaConfirma] = useState("");
    const [cep, setCep] = useState("");
    const [numero, setNumero] = useState("");
    const [dataNasc, setDataNasc] = useState(new Date());

    const [endereco, setEndereco] = useState("");
    const [dadosCep, setDadosCep] = useState({});

    const [errorLogin, setErrorLogin] = useState(false);

    function validarCep(event) {
        const cepDigitado = event.target.value
        setCep(cepDigitado);

        if (cep.length !== 7) {
            return;
        }

        axios.get(`https://viacep.com.br/ws/${cepDigitado}/json/`, {}, {
            'Content-Type': 'application/json',
            'Access-Control-Allow-Headers': 'Origin, X-Requested-With, Content-Type, Accept'
        })
        .then(e => {
            const dadosCepRes = e.data;
            setDadosCep(dadosCepRes);
            setEndereco(`${dadosCepRes.logradouro} ${numero ? `, ${numero}` : ''}, ${dadosCepRes.bairro}, ${dadosCepRes.localidade} - ${dadosCepRes.uf}`);
        }).catch(e => {
            console.error(e);
        });
    }

    function validarCadastro() {

        if (
            nome.trim() === "" ||
            cnpj.trim() === "" ||
            email.trim() === "" ||
            senha.trim() === "" ||
            senhaConfirma.trim() === "" ||
            cep.trim() === "" ||
            numero.trim() === "" ||
            dataNasc.trim() === ""
        ) { return; }

        if (senha !== senhaConfirma) {
            return;
        }
        
        const data = {
            nome,
            cnpj,
            email,
            senha,
            cep,
            numero,
            dataNasc,
        }
        
        console.log(data);

        api.post('/login', data, {
            'Content-Type': 'application/json',
            'Access-Control-Allow-Origin': '*',
            'Access-Control-Allow-Headers': 'Origin, X-Requested-With, Content-Type, Accept'
        }).then(e => {
            console.log(e.data);
            if (e.status === 200) {
                history.push('/login');
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
                <h3>Cadastro</h3>
            </div>

            <div className="entrar">

                <h3>Nome completo:</h3>
                <input
                    type="text"
                    name="nome"
                    id="nome_completo_cadastro"
                    onChange={(e) => setNome(e.target.value)}
                />

                <h3>CPF/CNPJ:</h3>
                <input
                    type="text"
                    name="cnpj"
                    id="cpf_cadastro"
                    onChange={(e) => setCnpj(e.target.value)}
                />

                <h3>E-mail:</h3>
                <input
                    type="text"
                    name="email"
                    id="nome_cadastro"
                    onChange={(e) => setEmail(e.target.value)}
                />

                <h3>Senha:</h3>
                <p>Conter no mínimo 6 caracteres</p>
                <input
                    type="password"
                    name="senha"
                    id="login_cadastro"
                    onChange={(e) => setSenha(e.target.value)}
                />

                <h3>Confirmar Senha:</h3>
                <input
                    type="password"
                    name="confirm_login"
                    id="confirmar_senha_cadastro"
                    onChange={(e) => setSenhaConfirma(e.target.value)}
                />

                <h3>CEP:</h3>
                <input
                    type="text"
                    name="cep"
                    id="cep_cadastro"
                    onChange={validarCep}
                />

                <h3>Número:</h3>
                <input
                    type="text"
                    name="numeroCasa"
                    id="numero_cadastro"
                    onChange={(e) => {
                        const numeroAtual = e.target.value;
                        setNumero(numeroAtual);
                        if (endereco) {
                            setEndereco(`${dadosCep.logradouro} ${numero ? `${numeroAtual}` : ''}, ${dadosCep.bairro}, ${dadosCep.localidade} - ${dadosCep.uf}`);
                        }
                    }}
                />

                <h3>Endereço:</h3>
                <input
                    className="input-disabled"
                    type="text"
                    name="endereco"
                    id="endereco_cadastro"
                    value={endereco}
                    disabled
                />

                <h3>Data de Nascimento:</h3>
                <input
                    type="date"
                    name="datanasc"
                    id="data_nascimento_cadastro"
                    onChange={(e) => setDataNasc(e.target.value)}
                />
 
                <button className="bt" onClick={validarCadastro}>
                    Cadastrar
                </button>
            </div>
            <Footer />
        </>
    )
}
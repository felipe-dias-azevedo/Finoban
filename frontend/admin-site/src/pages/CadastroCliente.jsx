import api from '../services/api';
import React, { useState } from 'react';
import { useHistory } from 'react-router';

function CadastroCliente() {

    const history = useHistory();

    const [nome, setNome] = useState("");
    const [cnpj, setCnpj] = useState("");
    const [email, setEmail] = useState("");
    const [senha, setSenha] = useState("");
    const [dataNasc, setDataNasc] = useState("");
    const [cep, setCep] = useState("");
    const [numero, setNumero] = useState("");

    function inserirCliente() {

        if (nome.trim() === "" ||
            cnpj.trim() === "" ||
            email.trim() === "" ||
            senha.trim() === "" ||
            dataNasc.trim() === "" ||
            cep.trim() === "" ||
            numero.trim() === "") {
            return;
        }

        const dataCliente = {
            nome,
            cnpj,
            email,
            senha,
            dataNasc,
            cep,
            numero
        }

        api.post('/cadastro', dataCliente, {
            'Content-Type': 'application/json',
            'Access-Control-Allow-Origin': '*',
            'Access-Control-Allow-Headers': 'Origin, X-Requested-With, Content-Type, Accept'
        }).then(e => {
            if(e.status == 201) {
                console.log(dataCliente)
                console.log("cadastrado");
                history.push("/admin/cliente/");    
            } else {
                console.log("deu erro");
            }
        }).catch(erro => {
            console.log(erro);
        });
    }

    return (
        <div id="cadastro">
            <section className="title-site">
                <h1>Cadastro Cliente</h1>
            </section>
            <div className="form-holder">
                <div className="input-holder">
                    <h3>CNPJ:</h3>
                    <input type="text" name="cnpj" onChange={(e) => setCnpj(e.target.value)}/>
                </div>
                <div className="input-holder">
                    <h3>Nome:</h3>
                    <input type="text" name="nome" onChange={(e) => setNome(e.target.value)}/>
                </div>
                <div className="input-holder">
                    <h3>Email:</h3>
                    <input type="email" name="email" onChange={(e) => setEmail(e.target.value)} />
                </div>
                <div className="input-holder">
                    <h3>Senha:</h3>
                    <input type="password" name="senha" onChange={(e) => setSenha(e.target.value)}/>
                </div>
                <div className="input-holder">
                    <h3>Data de nascimento:</h3>
                    <input type="date" name="dataNascimento" onChange={(e) => setDataNasc(e.target.value)}/>
                </div>
                <div className="input-holder">
                    <h3>CEP:</h3>
                    <input type="email" name="cep" onChange={(e) => setCep(e.target.value)}/>
                </div>
                <div className="input-holder">
                    <h3>Número:</h3>
                    <input type="text" name="numero" onChange={(e) => setNumero(e.target.value)}/>
                </div>
                <div className="button-holder">
                    <button onClick={inserirCliente}>Cadastrar</button>
                </div>
            </div>
        </div>
    );
}

export default CadastroCliente;
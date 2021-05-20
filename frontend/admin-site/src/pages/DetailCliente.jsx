import { useEffect, useState } from "react";
import { useHistory, useParams } from "react-router";
import { Link } from "react-router-dom";
import api from '../services/api';
import MainAdminCliente from "./MainAdminCliente";

function DetailCliente() {
    
    const history = useHistory();
    const params = useParams();

    const [cnpj, setCnpj] = useState("");
    const [nome, setNome] = useState("");
    const [email, setEmail] = useState("");
    const [cep, setCep] = useState("");
    const [numero, setNumero] = useState(0);
    const [dataNasc, setDataNasc] = useState("");

    function excluirUsuario() {
        api.delete(`/usuarios/${params.id}`, {}, {
            'Access-Control-Allow-Origin': '*',
            'Access-Control-Allow-Headers': 'Origin, X-Requested-With, Content-Type, Accept',
        }).then(e => {
            if (e.status === 200 || e.status === 204) {
                history.push("/admin/cliente/");
            } else {
                console.log("ERRO NO DELETE")
            }
        }).catch(e => {
            console.error(e);
        });
    }

    useEffect(() => {
        api.get(`/usuarios/${params.id}`, {}, {
            'Access-Control-Allow-Origin': '*',
            'Access-Control-Allow-Headers': 'Origin, X-Requested-With, Content-Type, Accept',
        }).then(e => {

            setCnpj(e.data.cnpj);
            setNome(e.data.nome);
            setEmail(e.data.email);
            setCep(e.data.cep);
            setNumero(e.data.numero);
            setDataNasc(e.data.dataNasc);

        }).catch(e => {
            console.error(e);
        });
    }, [params.id])



    return (
        <>
            <MainAdminCliente />
            <div className="content">
                <div id="myModal" className="modal">
                    <div className="modal-content">
                        <div className="modal-body">
                            <span className="close"><Link to="/admin/cliente">&times;</Link></span>
                            <br />
                            <div className="parts">
                                <div className="part-one-client">
                                    <h3>Nome:   <span id="dado">{nome}</span></h3>
                                    <h3>E-mail:   <span id="dado">{email}</span></h3>
                                    <h3>CEP: <span id="dado">{cep}</span></h3>
                                </div>
                                <div className="part-two-client">
                                    <h3>CNPJ:   <span id="dado">{cnpj}</span></h3>
                                    <h3>Data de nascimento:   <span id="dado">{dataNasc}</span></h3>
                                    <h3>Número:   <span id="dado">{numero}</span></h3>
                                </div>
                                <button onClick={excluirUsuario} id="button-edit-client">
                                    Excluir
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}

export default DetailCliente;
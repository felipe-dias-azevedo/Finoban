import { useEffect, useState } from "react";
import { Link, useHistory, useParams } from "react-router-dom";
import api from "../services/api";
import MainAdminRegiao from "./MainAdminRegiao";

function DetailRegiao() {

    const history = useHistory();
    const params = useParams();

    const [nome, setnome] = useState("")
    const [valor, setValor] = useState("")
    const [data, setData] = useState("")

    function excluirRegiao() {
        api.delete(`regioes/${params.id}`, {}, {
            'Access-Control-Allow-Origin': '*',
            'Access-Control-Allow-Headers': 'Origin, X-Requested-With, Content-Type, Accept',
        }).then(e => {
            if (e.status === 200 || e.status === 204) {
                history.push("/admin/regiao");
            } else {
                console.log("ERRO AO DELETAR")
            }
        }).catch(e => {
            console.error(e);
        });
    }

    useEffect(() => {
        api.get(`/regioes/${params.id}`, {}, {
            'Access-Control-Allow-Origin': '*',
            'Access-Control-Allow-Headers': 'Origin, X-Requested-With, Content-Type, Accept',
        }).then(e => {
            console.log(e.data); // PEGA O JSON 
            setnome(e.data.descricaoRegiao);
            setValor(e.data.valorRegiao);
            setData(e.data.dataCraw);

        }).catch(e => {
            console.error(e);
        });
    }, [params.id])
    
    return (
        <>
            <MainAdminRegiao />
            <div className="content">
                <div id="myModal" className="modal">
                    <div className="modal-content">
                        <div className="modal-body">
                        <span className="close"><Link to="/admin/regiao">&times;</Link></span>
                            <br />
                            <div className="parts">
                                <div className="part-one-client">
                                    <h3>Descrição:   <span id="dado">{nome}</span></h3>
                                    <h3>Valor:   <span id="dado">{valor}</span></h3>
                                    <h3>Data: <span id="dado">{data}</span></h3>
                                </div>
                                <button onClick={excluirRegiao} id="button-edit-client">
                                    Excluir
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
}

export default DetailRegiao;
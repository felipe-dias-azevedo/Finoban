import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import Regiao from "../components/Admin/Regiao";
import api from '../services/api'

function MainAdminRegiao() {

    const [regioes, setRegioes] = useState([])

    useEffect(() => {

        async function getRegioes() {
            const res = await api.get("/regioes");
            setRegioes(res.data);
        }
        getRegioes();
        
    }, [])


    return (
        <div class="content">
            <div className="modal-tittle-admin">
                <h1>Home Administrador</h1>
            </div>
            <div className="modal-content-admin">
                <div className="welcome-admin">
                    <h3>Seja bem vindo, <span id="admin-title">administrador</span></h3>
                </div>
                <div className="content-dates">
                    <div className="menu">
                        <ul>
                            <li><Link to="/admin/cliente/">Gerenciamento dos usuários</Link></li>
                            <li id="selected"><Link to="/admin/regiao/">Gerenciamento das regiões</Link></li>
                            <li><Link to="/admin/acesso/">Gerenciamento de acessos</Link></li>
                        </ul>
                    </div>
                    <div className="dates">
                        <div className="quantity-clients">
                            <h1>Quantidade de regiões cadastradas <span>{regioes.length}</span></h1>
                        </div>
                        <div className="clients">
                            {
                                
                                regioes.map((regiao) => (
                                    <Regiao id={regiao.idRegiao} nome={regiao.descricaoRegiao} valor={regiao.valorRegiao} data={regiao.dataCraw} />
                                ))

                            }
                        </div>
                        <button id="button-insert-client">
                            <Link to="/admin/register/regiao">Inserir nova região</Link>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default MainAdminRegiao;
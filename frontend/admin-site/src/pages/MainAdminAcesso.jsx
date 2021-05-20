import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import Acesso from "../components/Admin/Acesso";
import api from "../services/api";


function MainAdminAcesso() {

    const [acessom, setAcesso]= useState([]);

    useEffect(() => {
        api.get("/acessos").then(e =>{
            setAcesso(e.data)
        }).catch(e => {
            console.error(e)
        })
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
                            <li><Link to="/admin/regiao/">Gerenciamento das regiões</Link></li>
                            <li id="selected"><Link to="/admin/acesso/">Gerenciamento de acessos</Link></li>
                        </ul>
                    </div>
                    <div className="dates">
                        <div className="quantity-clients">
                            <h1>Quantidade de acessos esta semana <span>2456</span></h1>
                        </div>
                        <div className="clients">
                            <Acesso id="1" nome="Testando esta beleza" cnpj="00000" email="testando.beleza@beleza.com"/>
                        </div>
                        <button id="button-insert-client">
                            <Link to="/admin/register/acesso">Inserir novo acesso</Link>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default MainAdminAcesso;
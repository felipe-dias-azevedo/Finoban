import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import api from "../services/api";
import Cliente from "../components/Admin/Cliente";

export default function MainAdminCliente() {    

    const [usuarios, setUsuarios] = useState([]);
    const [updateSite, setUpdateSite] = useState(0);

    // setInterval(() => {
    //     setUpdateSite(updateSite+1);
    // }, 6000);

    useEffect(() => {
        api.get("/usuarios").then(e => {
            setUsuarios(e.data)
            // for (let i = 0; i < e.data.length; i++) {
            //     console.log("id: " + e.data[i].id + ", cnpj: " + e.data[i].cnpj + ", nome: " + e.data[i].nome + ", email: " + e.data[i].email);
            // }
        }).catch(e => {
            console.error(e)
        }) 
    }, [updateSite]);



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
                            <li id="selected"><Link to="/admin/cliente/">Gerenciamento dos usuários</Link></li>
                            <li><Link to="/admin/regiao/">Gerenciamento das regiões</Link></li>
                            <li><Link to="/admin/acesso/">Gerenciamento de acessos</Link></li>
                        </ul>
                    </div>
                    <div className="dates">
                        <div className="quantity-clients">
                            <h1>Quantidade de clientes cadastrados <span>{usuarios.length}</span></h1>
                        </div>
                        <div className="clients">
                            {usuarios.map(e => {
                                return (
                                    <Cliente id={e.id} cnpj={e.cnpj} nome={e.nome} email={e.email} />
                                )
                            })}
                        </div>
                        <button id="button-insert-client">
                            <Link to="/admin/register/cliente">Inserir novo usuário</Link>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    );
}
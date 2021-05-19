import { Link } from "react-router-dom";
import MainAdminRegiao from "./MainAdminRegiao";

function DetailRegiao() {
    return (
        <>
            <MainAdminRegiao />
            <div class="content">
                <div id="myModal" className="modal">
                    <div className="modal-content">
                        <div className="modal-body">
                        <span className="close"><Link to="/admin/regiao">&times;</Link></span>
                            <br />
                            <div className="parts">
                                <div className="part-one-client">
                                    <h3>Nome:   <span id="dado">Silvio Santos</span></h3>
                                    <h3>E-mail:   <span id="dado">silvio@santos.com</span></h3>
                                    <h3>CEP: <span id="dado">01414001</span></h3>
                                </div>
                                <div className="part-two-client">
                                    <h3>CNPJ:   <span id="dado">123</span></h3>
                                    <h3>Data de nascimento:   <span id="dado">01/01/1970</span></h3>
                                    <h3>Número:   <span id="dado">591</span></h3>
                                </div>
                                <button id="button-edit-client">
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
import { Link } from "react-router-dom";
import MainAdminAcesso from "./MainAdminAcesso";

function DetailAcesso({ }) {
    return (
        <>
            <MainAdminAcesso />
            <div class="content">
                <div id="myModal" class="modal">
                    <div class="modal-content">
                        <div class="modal-body">
                            <span className="close"><Link to="/admin/acesso">&times;</Link></span>
                            <br />
                            <div class="parts">
                                <div class="part-one-client">
                                    <h3>fkCliente:   <span id="dado">Silvio Santos</span></h3>
                                    <h3>fkRegiao:   <span id="dado">silvio@santos.com</span></h3>
                                    <h3>renda: <span id="dado">01414001</span></h3>
                                    <h3>valorImovel: <span id="dado">01414001</span></h3>
                                    <h3>tempoFinanciamento: <span id="dado">01414001</span></h3>
                                    <h3>porcentagemRenda: <span id="dado">01414001</span></h3>
                                </div>
                                <div class="part-two-client">
                                    <h3>BancoEscolhidoEnum: <span id="dado">01414001</span></h3>
                                    <h3>dataHoraEntrada:   <span id="dado">123</span></h3>
                                    <h3>dataHoraSaida:   <span id="dado">01/01/1970</span></h3>
                                    <h3>statusSaida:   <span id="dado">591</span></h3>
                                    <h3>paginaSaida:   <span id="dado">591</span></h3>
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
export default DetailAcesso;
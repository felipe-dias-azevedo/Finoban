import { useEffect, useState } from "react";
import { Link, useHistory, useParams } from "react-router-dom";
import api from "../services/api";
import MainAdminAcesso from "./MainAdminAcesso";

function DetailAcesso({ }) {

    const history = useHistory();
    const params = useParams();

    const [renda, setRenda] = useState(0);
    const [valorImovel, setValorImovel] = useState(0);
    const [tempoFinanciamento, setTempoFinanciamento] = useState(0);
    const [porcentagemRenda, setPorcentagemRenda] = useState(0);
    const [bancoEscolhido, setBancoEscolhido] = useState(0);
    const [dataHoraEntrada, setDataHoraEntrada] = useState("");
    const [dataHoraSaida, setDataHoraSaida] = useState("");
    const [statusaida, setStatusSaida] = useState(0);
    const [paginaSaida, setPaginaSaida] = useState(0);
    const [fkRegiao, setfkRegiao] = useState(0);
    const [fkCliente, setfkCliente] = useState(0);


    function excluirAcesso() {
        api.delete(`regioes/${params.id}`, {}, {
            'Access-Control-Allow-Origin': '*',
            'Access-Control-Allow-Headers': 'Origin, X-Requested-With, Content-Type, Accept',
        }).then(e => {
            if (e.status === 200 || e.status === 204) {
                history.push("/admin/acesso");
            } else {
                console.log("ERRO AO DELETAR")
            }
        }).catch(e => {
            console.error(e);
        });
    }

    useEffect(() => {
        api.get(`/acessos/${params.id}`, {}, {
            'Access-Control-Allow-Origin': '*',
            'Access-Control-Allow-Headers': 'Origin, X-Requested-With, Content-Type, Accept',
        }).then(e => {
            setRenda(e.data.renda);
            setValorImovel(e.data.valorImovel);
            setTempoFinanciamento(e.data.tempoFinanciamento);
            setPorcentagemRenda(e.data.porcentagemRenda);
            setBancoEscolhido(e.data.bancoEscolhido);
            setDataHoraEntrada(e.data.dataHoraEntrada);
            setDataHoraSaida(e.data.dataHoraSaida);
            setStatusSaida(e.data.statusSaida);
            setPaginaSaida(e.data.paginaSaida);
            setfkRegiao(e.data.fkRegiao);
            setfkCliente(e.data.fkCliente);

        }).catch(e => {
            console.error(e);
        });
    }, [params.id])



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
                                    <h3>Cliente:   <span id="dado">{fkCliente.nome}</span></h3>
                                    <h3>Região:   <span id="dado">{fkRegiao.descricaoRegiao}</span></h3>
                                    <h3>renda: <span id="dado">{renda}</span></h3>
                                    <h3>Valor Imovel: <span id="dado">{valorImovel}</span></h3>
                                    <h3>Tempo Financiamento: <span id="dado">{tempoFinanciamento}</span></h3>
                                    <h3>Porcentagem Renda: <span id="dado">{porcentagemRenda}</span></h3>
                                </div>
                                <div class="part-two-client">
                                    <h3>Banco Escolhido: <span id="dado">{bancoEscolhido}</span></h3>
                                    <h3>Data e Hora de Entrada:   <span id="dado">{dataHoraEntrada}</span></h3>
                                    <h3>Data Hora de Saida:   <span id="dado">{dataHoraSaida}</span></h3>
                                    <h3>Status de Saida:   <span id="dado">{statusaida}</span></h3>
                                    <h3>Pagina de Saida:   <span id="dado">{paginaSaida}</span></h3>
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
import React, { useState } from 'react';
import CampoItem from '../components/CampoItem';
import Footer from '../components/Footer';
import Header from '../components/HeaderDashboard';
import { RangeStepInput } from 'react-range-step-input';
import GoogleChart from '../components/GoogleChart';
import { useHistory } from 'react-router';
import Like from '../assets/images/like.svg';
import Deslike from '../assets/images/deslike.svg';
import BtnClose from '../assets/images/btn-close.svg';
import Api from '../services/api';
import Modal from 'react-bootstrap/Modal';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import FormGroup from 'react-bootstrap/FormGroup';
import { mockComponent } from 'react-dom/test-utils';
import NumberFormat from 'react-number-format';

function ModalFeedback(props) {

    const [feedback, setFeedback] = useState("");
    
    function avaliacaoLike() {

        const dataAvaliacao = {
            avalPositivo: 0,
            feedbackAval: feedback,
            fkAcesso: {
                idEntrada: 13
            }
        };

        Api.post('/avaliacao', dataAvaliacao, {
        }).then(e => {
            console.log(e.data);
            if (e.status === 201) {
                console.log("ok");
                props.onHide();
            } 
        }).catch(e => {
            console.error(e);
        });

    }

    return (
        <Modal
            {...props}
            size="lg"
            aria-labelledby="contained-modal-title-vcenter"
            centered
        >
            <Modal.Header closeButton>
                <Modal.Title id="contained-modal-title-vcenter">
                    Nos dê um feedback
            </Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Form.Group controlId="exampleForm.ControlTextarea1">
                    <Form.Label name="feedback" v>Digite sua mensagem</Form.Label>
                    <Form.Control as="textarea" rows={2} name="feedback" onChange={(e) => setFeedback(e.target.value)} />
                </Form.Group>
            </Modal.Body>
            <Modal.Footer>
                <button onClick={props.onHide} className="btn-avaliacao-cancelar">Fechar</button>
                <button onClick={avaliacaoLike} className="btn-avaliacao-enviar">Enviar</button>

            </Modal.Footer>
        </Modal>
    );
}

function ModalContratar(props) {

    const [modalShowSucesso, setModalShowSucesso] = React.useState(false);
    const [modalShowContratar, setModalShowContratar] = React.useState(false);

    function IrWhatsapp() {
        window.location.href = "https://wa.me/551131758248";
    }

    function IrEmail() {
        window.location.href = "mailto: safra@safra.com.br";
    }

    return (
        <Modal
            {...props}
            size="lg"
            aria-labelledby="contained-modal-title-vcenter"
            centered
        >
            <Modal.Header closeButton>
                <Modal.Title>
                    <p className="cor-verde">De que forma você gostaria de entrar em contato?</p>
            </Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Form.Group controlId="exampleForm.ControlTextarea1">
                    <div className="d-flex justify-content-center">
                    <Form.Label className="cor-verde">
                     Gostaria de agendar um horário? <input type="checkbox" id="agendar-horario"></input>
                        <br />

                        <div className="mt-3" />
                        <input type="date" className="input-data"></input>
                        <input type="time" id="input-hora"></input>
                        </Form.Label>
                    </div>
                    <br />
                    
                    <div className="d-flex flex-row">
                    <button className="btn-contratar" onClick={() => {}} >Telefone</button>
                    <button className="btn-contratar" onClick={() => IrWhatsapp()}>WhatsApp</button>
                    <button className="btn-contratar" onClick={() => IrEmail()}>E-mail</button>
                    <button className="btn-contratar">Visita na Agência</button>
                    </div>

                    <ModalSucesso
                show={modalShowSucesso}
                onHide={() => setModalShowSucesso(false)}
            />

                </Form.Group>
            </Modal.Body>
        </Modal>
    );
}

function ModalSucesso(props) {

    return (
        <Modal
            {...props}
            size="lg"
            aria-labelledby="contained-modal-title-vcenter"
            centered
        >
            <Modal.Header closeButton>
                <Modal.Title>
                    <p className="cor-verde">Parabéns!!</p>
            </Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Form.Group controlId="exampleForm.ControlTextarea1">
                    <div className="d-flex justify-content-center">
                    <Form.Label className="cor-verde">
                    O Banco Cifra irá entrar em contato com você!
                        <br />

                        </Form.Label>
                    </div>
                    <br />
                
                </Form.Group>
            </Modal.Body>
        </Modal>
    );
}

function Dashboard() {

    window.onbeforeunload = confirmExit;
    function confirmExit(){
      reqAcesso(1);
      return "You have attempted to leave this page.  If you have made any changes to the fields without clicking the Save button, your changes will be lost.  Are you sure you want to exit this page?";
    }

    const history = useHistory();
    const [modalShowFeedback, setModalShowFeedback] = React.useState(false);
    const [modalShowContratar, setModalShowContratar] = React.useState(false);

    var testeRecebido = localStorage.getItem("testeChave");
    let dataSimulacao = JSON.parse(testeRecebido);

    var tempoFinanciamento = parseInt(dataSimulacao.tempoFinanciamento);
    var valorImovelRecebido = parseFloat(dataSimulacao.valorImovel);

    console.log(dataSimulacao);

    var respostaSimulacao = JSON.parse(localStorage.getItem("respostaFinanciamento"));
    console.log(respostaSimulacao);
    var valorTaxa = respostaSimulacao[0].data.taxa;

    var imovel;
    var taxa_juros;
    var taxa_juros_mensal;
    var valor_a_pagar;
    var anos_pagos;
    var prestacoes;
    var prestacao = {};

        imovel = valorImovelRecebido;
        taxa_juros = valorTaxa;
        taxa_juros_mensal = valorTaxa;
        valor_a_pagar = 0;
        anos_pagos = 0;
        prestacoes = [];
        
        while (anos_pagos < tempoFinanciamento) {
            anos_pagos++;
            var prestacao_sem_taxa = imovel / tempoFinanciamento;
            var prestacao_com_taxa = imovel * taxa_juros_mensal;
            if (anos_pagos == 1) {
                var primeira_prestacao = (prestacao_com_taxa / 12) + (prestacao_sem_taxa / 12);
            }
            imovel = imovel - (prestacao_sem_taxa);
            valor_a_pagar += (prestacao_sem_taxa + prestacao_com_taxa);
            prestacoes.push(prestacao_sem_taxa.toFixed(2) + prestacao_com_taxa.toFixed(2));
        }
        console.log(prestacoes);

    var taxaSimulacao = respostaSimulacao[2].data.taxa + "% a.a.";

    const anoInicial = new Date().getFullYear();
    const anoFinal = anoInicial + tempoFinanciamento;
    const [value, setValue] = useState(((anoInicial + anoFinal) / 2).toFixed());

    var horarioEntrada = sessionStorage.getItem("horarioEntrada");
    var idUsuario = localStorage.getItem("idUsuario");
    
    var data = new Date();
    var dia = String(data.getDate()).padStart(2, '0');
    var mes = String(data.getMonth() + 1).padStart(2, '0');
    var ano = data.getFullYear();

    var hora = data.getHours();
    var minutos = data.getMinutes();
    if(minutos < 10) {
        minutos = '0' + minutos;
    }
    var segundos = data.getUTCSeconds();
    if(segundos < 10) {
        segundos = '0' + segundos;
    }
    var milisegundos = data.getMilliseconds();

    var dataSaida = ano + '-' + mes + '-' + dia + 'T' + hora + ':' + minutos + ':' + segundos + '.' + milisegundos;

    console.log(dataSaida);

    var porcentagemRenda = localStorage.getItem("porcentagemRenda");

    function reqAcesso(confirmouContratacao) {
    var acesso = {
        dataHoraEntrada: horarioEntrada,
        dataHoraSaida: dataSaida,
        paginaSaida: 4,
        statusSaida: confirmouContratacao,
        renda: dataSimulacao.renda,
        valorImovel: dataSimulacao.valorImovel,
        tempoFinanciamento: dataSimulacao.tempoFinanciamento,
        porcentagemRenda: parseInt(porcentagemRenda),
        bancoEscolhido: 2,
        fkRegiao: {
            idRegiao: 2000
        },
        fkCliente: {
            id: parseInt(idUsuario)
        },
    }

    console.log(acesso);

    Api.post('/acessos', acesso, {
    }).then(e => {
      console.log(e.data);
    }).catch(e => {
      console.error(e)
    });
}       

    function avaliacaoDeslike() {

        const dataAvaliacao = {
            avalPositivo: 1,
            feedbackAval: "Deslike",
            fkAcesso: {
                idEntrada: 13
            }
        };

        Api.post('/avaliacao', dataAvaliacao, {
        }).then(e => {
            console.log(e.data);
            if (e.status === 201) {
                console.log("ok");
                alert("Recebemos a sua avaliação, obrigado!")
            } else {
                console.log("erro");
            }
        }).catch(e => {
            console.error(e);
            alert("Ocorreu um erro")
        });

    }
    
    return (
        <>
            <Header />
            <div className="center">
                <div className="box box-titulo center">
                    <h1>Dashboard Comparativo</h1>
                </div>
                <div className="box box-subtitulo center">
                    <h4>Prazo de pagamento:</h4>
                </div>

                <RangeStepInput
                    min={anoInicial} max={anoFinal}
                    value={value} step={1}
                    onChange={(e) => {
                        setValue(e.target.value);
                    }}
                />
                <span className="anoFin"> {value} </span>

                <div className="box-campos">
                    <div className="box box-label center">
                        <p className="fw-500">Banco:</p>
                    </div>
                    <div className="box box-label center">
                        <p class="fw-500">Valor Imóvel:</p>
                    </div>
                </div>
                <div className="box-campos">
                    <select>
                        <option value="1">Banco Cifra</option>
                        <option value="2">Banco do Presil</option>
                        <option value="3">S16 Bank</option>
                    </select>
                    <input type="text" value={valorImovelRecebido.toFixed(2)} disabled />
                </div>
                <div className="box-campos">
                    <CampoItem label="Taxa" valor={taxaSimulacao} />
                    <CampoItem label="Primeira Parcela" valor="R$ 3.200,00" />
                    <CampoItem label="Parcela Anterior" valor="R$ 2.500,00" />
                    <CampoItem label="Próxima Parcela" valor="R$ 2.000,00" />
                    <CampoItem label="Última Parcela" valor="R$ 2.200,00" />
                </div>
                <div className="box-campos">
                <div className="box box-item center">
                    <p>Valor final do imóvel</p>
                    <br />
                    <NumberFormat value={valor_a_pagar.toFixed(2)} displayType={'text'} thousandSeparator={true} prefix={'R$'} />

                    </div>
                    <div className="grafico">
                        <GoogleChart />
                    </div>

                    
                </div>
                <div className="box-campos">
                    <a href="http://localhost:8080/api-finoban/download/csv" className="botao">
                        Salvar e Baixar CSV
                    </a>
                    <button className="botao" onClick={() => { reqAcesso(0); setModalShowContratar(true);}}>Gostei, quero contratar</button>
                </div>
            </div>

            <div className="alert-avaliacao">
                <img src={BtnClose} className="btn-close" />
                <h1 className="titulo-avaliacao">Avalie nosso serviço</h1>
                <div className="btn-avaliacao">
                    <img src={Like} alt="" onClick={() => setModalShowFeedback(true)} />
                    <img src={Deslike} alt="" onClick={avaliacaoDeslike} />
                </div>
            </div>

            <ModalFeedback
                show={modalShowFeedback}
                onHide={() => setModalShowFeedback(false)}
            />

            <ModalContratar
                show={modalShowContratar}
                onHide={() => setModalShowContratar(false)}
            />


            <Footer />
        </>
    );
}

export default Dashboard;
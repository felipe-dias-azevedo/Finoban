import React, { useState } from 'react';
import CampoItem from '../components/CampoItem';
import Footer from '../components/Footer';
import Header from '../components/Header';
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
                        Gostaria de agendar um horário?
                        </Form.Label>
                    </div>
                    <br />
                    
                    <div className="d-flex flex-row">
                    <button className="btn-contratar">Telefone</button>
                    <button className="btn-contratar">WhatsApp</button>
                    <button className="btn-contratar">E-mail</button>
                    <button className="btn-contratar">Visita na Agência</button>
                    </div>

                </Form.Group>
            </Modal.Body>
        </Modal>
    );
}


function Dashboard() {
    const history = useHistory();

    const [modalShowFeedback, setModalShowFeedback] = React.useState(false);
    const [modalShowContratar, setModalShowContratar] = React.useState(false);

    var testeRecebido = localStorage.getItem("testeChave");
    let dataSimulacao = JSON.parse(testeRecebido);
    var tempoFinanciamentoRecebido = parseInt(dataSimulacao.tempoFinanciamento);
    var valorImovelRecebido = parseFloat(dataSimulacao.valorImovel);

    const anoInicial = new Date().getFullYear();
    const anoFinal = anoInicial + tempoFinanciamentoRecebido;
    const [value, setValue] = useState(((anoInicial + anoFinal) / 2).toFixed());

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
            } else {
                console.log("erro");
            }
        }).catch(e => {
            console.error(e);
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
                        <p>Banco:</p>
                    </div>
                    <div className="box box-label center">
                        <p>Valor Imóvel:</p>
                    </div>
                </div>
                <div className="box-campos">
                    <select name="" id="">
                        <option value="1">Banco Cifra</option>
                        <option value="1">Banco do Presil</option>
                        <option value="1">S16 Bank</option>
                    </select>
                    <input type="text" value={valorImovelRecebido} disabled />
                </div>
                <div className="box-campos">
                    <CampoItem label="Taxa" valor="7% a.a." />
                    <CampoItem label="Primeira Parcela" valor="R$ 3.200,00" />
                    <CampoItem label="Parcela Anterior" valor="R$ 2.500,00" />
                    <CampoItem label="Próxima Parcela" valor="R$ 2.000,00" />
                    <CampoItem label="Parcela 2027" valor="R$ 2.200,00" />
                </div>
                <div className="box-campos">
                    <CampoItem label="Valor Total do Imóvel" valor="R$ 1.050.000,00" />
                    <div className="grafico">
                        <GoogleChart />
                    </div>
                </div>
                <div className="box-campos">
                    <a href="http://localhost:8080/api-finoban/download/csv" className="botao">
                        Salvar e Baixar CSV
                    </a>
                    <button className="botao" onClick={() => setModalShowContratar(true)}>Gostei, quero contratar</button>
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
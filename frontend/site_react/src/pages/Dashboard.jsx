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


function MyVerticallyCenteredModal(props) {
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
    <Form.Label>Digite sua mensagem</Form.Label>
    <Form.Control as="textarea" rows={2} />
  </Form.Group>
        </Modal.Body>
        <Modal.Footer>
            <button onClick={props.onHide} className="btn-avaliacao-cancelar">Fechar</button>
            <button className="btn-avaliacao-enviar">Enviar</button>

        </Modal.Footer>
      </Modal>
    );
  }


function Dashboard() {
    const history = useHistory();

    const [modalShow, setModalShow] = React.useState(false);

    const anoInicial = 2021;
    const anoFinal = anoInicial + 35;
    const [value, setValue] = useState(((anoInicial + anoFinal) / 2).toFixed());

    function avaliacaoPositivo() {

        // Mock
        const dataAvaliacao = {
            avalPositivo: "like",
            feedbackAval: "a",
            dataAval: "2021-09-20 09:00:00",
            fkAcesso: {
                idEntrada: 13
            }
        };

        console.log(dataAvaliacao);

        Api.post('/avaliacao', dataAvaliacao, {
            'Content-Type': 'application/json',
            'Access-Control-Allow-Origin': '*',
            'Access-Control-Allow-Headers': 'Origin, X-Requested-With, Content-Type, Accept'
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
                    <input type="text" name="" id="" />
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
                    <a href="http://localhost:8080/api-finoban/download/txt" className="botao">
                        Salvar e Baixar TXT
                    </a>
                    <button className="botao">Gostei, quero contratar</button>
                </div>
            </div>

            <div className="alert-avaliacao">
                <img src={BtnClose} className="btn-close" />
                <h1 className="titulo-avaliacao">Avalie nosso serviço</h1>
                <div className="btn-avaliacao">
                    <img src={Like} alt="" onClick={() => setModalShow(true)}/>
                    <img src={Deslike} alt="" />
                </div>
            </div>

      <MyVerticallyCenteredModal
        show={modalShow}
        onHide={() => setModalShow(false)}
      />

            <Footer />
        </>
    );
}

export default Dashboard;
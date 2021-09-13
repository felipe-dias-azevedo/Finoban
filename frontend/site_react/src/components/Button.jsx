import React from 'react';
import { Link } from 'react-router-dom';
import Modal from 'react-bootstrap/Modal';
import { useHistory } from 'react-router';
import ButtonBootstrap from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import FormGroup from 'react-bootstrap/FormGroup';


function ModalLogin(props) {

    const history = useHistory();

    function IrLogin() {
        history.push('/login');
    }

    return (
        <Modal
            {...props}
            size="xs"
            aria-labelledby="contained-modal-title-vcenter"
            centered
        >
            <Modal.Header closeButton>
                <Modal.Title>
                    <p className="cor-verde fonte-20">Gostou da nossa proposta?</p>
                </Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Form.Group controlId="exampleForm.ControlTextarea1">
                    <div className="d-flex justify-content-center">
                        <Form.Label className="cor-verde w-40 fonte-15">
                            Faça login para utilizar de todos os nossos recursos
                        </Form.Label>
                    </div>
                    <br />

                    <div className="d-flex flex-row justify-content-around">
                        <button className="btn-proposta fonte-15" onClick={props.onHide}>Não fazer login</button>
                        <button className="btn-proposta fonte-15 bg-verde" onClick={IrLogin}>Fazer Login</button>
                    </div>

                </Form.Group>
            </Modal.Body>
        </Modal>
    );
}

function Button() {

    const [modalShow, setModalShow] = React.useState(false);

    return (
        <>
            <button className="btn" onClick={() => setModalShow(true)}>Ver mais</button>

            <ModalLogin
                show={modalShow}
                onHide={() => setModalShow(false)}
            />
        </>
    );
}

export default Button;
import React from 'react';
import Modal from "react-bootstrap/Modal";
import Form from "react-bootstrap/Form";
import { useState } from 'react';
import api from '../../services/api';

function ModalFeedback(props) {
	const [feedback, setFeedback] = useState("");

	const efetuarAvaliacao = async () => {
		const dataAvaliacao = {
			avalPositivo: 0,
			feedbackAval: feedback,
			fkAcesso: {
				idEntrada: 13,
			},
		};

        try {
            const response = await api.post('/avaliacao', dataAvaliacao);
            props.onHide();
        } catch (error) {
            console.log(error)
        }
	};

	return (
		<Modal
			{...props}
			size="xs"
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
					<Form.Label name="feedback" className="fonte-16">
						Digite sua mensagem
					</Form.Label>
					<Form.Control
						as="textarea"
						rows={2}
						name="feedback"
						onChange={(e) => setFeedback(e.target.value)}
					/>
				</Form.Group>
			</Modal.Body>
			<Modal.Footer>
				<div
					onClick={props.onHide}
					className="btn-avaliacao btn-avaliacao-cancelar"
				>
					Fechar
				</div>
				<div onClick={efetuarAvaliacao} className="btn-avaliacao">
					Enviar
				</div>
			</Modal.Footer>
		</Modal>
	);
}

export default ModalFeedback;
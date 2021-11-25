import React from 'react';
import Modal from "react-bootstrap/Modal";
import Form from "react-bootstrap/Form";

function ModalContratar(props) {
	const [showAgendarHorario, setShowAgendarHorario] = React.useState(false);

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
					<p className="cor-verde fonte-20">
						De que forma você gostaria de entrar em contato?
					</p>
				</Modal.Title>
			</Modal.Header>
			<Modal.Body>
				<Form.Group controlId="exampleForm.ControlTextarea1">
					<div className="d-flex justify-content-center mt-3">
						<Form.Label className="cor-verde fonte-16">
							Gostaria de agendar um horário?{" "}
							<input
								type="checkbox"
								id="agendar-horario"
								onClick={() => {
									if (!showAgendarHorario)
										setShowAgendarHorario(true);
									else setShowAgendarHorario(false);
								}}
							></input>
							<div
								className={
									showAgendarHorario ? "display-block" : "display-none"
								}
							>
								<input
									type="date"
									className="input-data"
								></input>
								<input type="time" id="input-hora"></input>
							</div>
						</Form.Label>
					</div>
					<br />

					<div className="d-flex flex-row">
						<div className="btn-contato" onClick={() => {}}>
							Telefone
						</div>
						<div
							className="btn-contato"
							onClick={() => IrWhatsapp()}
						>
							WhatsApp
						</div>
						<div className="btn-contato" onClick={() => IrEmail()}>
							E-mail
						</div>
						<div className="btn-contato">Visita na Agência</div>
					</div>
				</Form.Group>
			</Modal.Body>
		</Modal>
	);
}

export default ModalContratar;
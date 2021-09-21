import React from "react";
import Footer from "../components/Footer";
import Header from "../components/Header";
import Slider from "../components/Slider";
import Carousel from "react-bootstrap/Carousel";
import Seguranca from "../assets/images/seguranca.png";
import Regulamentacao from "../assets/images/regulamentacao.png";
import Simplicidade from "../assets/images/simplicidade.png";
import Liberdade from "../assets/images/liberdade.png";
import Competitividade from "../assets/images/competitividade.png";

function Openbanking() {
	return (
		<>
			<Header />

			<Carousel>
				<Carousel.Item>
					<img className="d-block w-100" src={Liberdade} />
				</Carousel.Item>
				<Carousel.Item>
					<img className="d-block w-100" src={Simplicidade} />
				</Carousel.Item>
				<Carousel.Item>
					<img className="d-block w-100" src={Competitividade} />
				</Carousel.Item>

				<Carousel.Item>
					<img className="d-block w-100" src={Seguranca} />
				</Carousel.Item>

				<Carousel.Item>
					<img className="d-block w-100" src={Regulamentacao} />
				</Carousel.Item>
			</Carousel>


			<div className="shadow openbanking-text box-text-openbanking">
				<h2>O que é Open Banking? </h2>
				<br />
				<br />
				<p>
					De modo geral, o principal objetivo do open banking é
					facilitar o acesso do público a operações financeiras,
					produtos e serviços. Para essa finalidade, são usados os
					dados dos próprios usuários — desde que eles permitam.
				</p>
			</div>
			<Footer />
		</>
	);
}

export default Openbanking;

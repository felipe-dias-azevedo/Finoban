import Header from "../../components/Header";
import Footer from "../../components/Footer";
import TesteProjetoCard from "../../components/AnaliseTeste/TesteProjetoCard";
import api from "../../services/api";

function TestesProjetos() {
    
	const getTestes = async () => {
		try {
			const response = await api.get("/tests/apps");
			console.log(response);
		} catch (error) {
			console.log(error);
		}
	};

    getTestes();

	return (
		<>
			<Header />

			<div className="titulo-teste-projeto">
				<h5> Aplicação </h5>
				<h5> Estatus </h5>
				<h5> Data teste </h5>
				<h5> Porcent. de sucesso </h5>
			</div>

			<TesteProjetoCard
				nome_aplicacao="API Finoban"
				estatus={true}
				data="24 Out 2022, 21:31:09"
				porcentagem_sucesso={80}
			/>

			<Footer />
		</>
	);
}

export default TestesProjetos;

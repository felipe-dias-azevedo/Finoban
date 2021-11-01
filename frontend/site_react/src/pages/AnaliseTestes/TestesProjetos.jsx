import Header from "../../components/Header";
import Footer from "../../components/Footer"
import TesteProjetoCard from "../../components/AnaliseTeste/TesteProjetoCard"

function TestesProjetos() {

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
            nome_aplicacao='API Finoban'
            estatus={true}
            data='24 Out 2022, 21:31:09'
            porcentagem_sucesso={80}
            />

            <Footer />
        </>
    )
}

export default TestesProjetos;
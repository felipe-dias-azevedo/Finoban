import { Link } from "react-router-dom";

function Regiao({ id, nome, valor, data }) {
    return (
        <>
            <div class="client">
                <Link to={`/admin/detail/regiao/${id}`}>
                    <h3>Nome Região: <span>{nome}</span></h3>
                    <h3>Valor Região: <span>{valor}</span></h3>
                    <h3>Data Crawler: <span>{data}</span></h3>
                </Link>
            </div>
        </>
    );
}

export default Regiao;
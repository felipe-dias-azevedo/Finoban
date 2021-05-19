import { Link } from "react-router-dom";

function Acesso({ id, nome, valor, data }) {
    return (
        <>
            <div class="client">
                <Link to={`/admin/detail/acesso/${id}`}>
                    <h3>Nome: <span>{nome}</span></h3>
                    <h3>Valor Região: <span>{valor}</span></h3>
                    <h3>Data: <span>{data}</span></h3>
                </Link>
            </div>
        </>
    );
}

export default Acesso;
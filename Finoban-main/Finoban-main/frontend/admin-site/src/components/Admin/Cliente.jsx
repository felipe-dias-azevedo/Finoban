import { Link } from "react-router-dom";

function Cliente({ id, cnpj, nome, email }) {
    return (
        <>
            <div class="client">
                <Link to={`/admin/detail/cliente/${id}`}>
                    <h3>CNPJ: <span>{cnpj}</span></h3>
                    <h3>Nome: <span>{nome}</span></h3>
                    <h3>E-mail: <span>{email}</span></h3>
                </Link>
            </div>
        </>
    );
}

export default Cliente;
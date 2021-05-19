
function CadastroCliente() {
    return (
        <div id="cadastro">
            <section className="title-site">
                <h1>Cadastro Cliente</h1>
            </section>
            <div className="form-holder">
                <div className="input-holder">
                    <h3>CNPJ:</h3>
                    <input type="text" />
                </div>
                <div className="input-holder">
                    <h3>Nome:</h3>
                    <input type="text" />
                </div>
                <div className="input-holder">
                    <h3>Email:</h3>
                    <input type="email" />
                </div>
                <div className="input-holder">
                    <h3>Data de nascimento:</h3>
                    <input type="date" />
                </div>
                <div className="input-holder">
                    <h3>CEP:</h3>
                    <input type="email" />
                </div>
                <div className="input-holder">
                    <h3>Número:</h3>
                    <input type="text" />
                </div>
                <div className="button-holder">
                    <button>Cadastrar</button>
                </div>
            </div>
        </div>
    );
}

export default CadastroCliente;
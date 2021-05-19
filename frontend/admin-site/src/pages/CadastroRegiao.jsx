
function CadastroRegiao() {
    return (
        <div id="cadastro">
            <section className="title-site">
                <h1>Cadastro Região</h1>
            </section>
            <div className="form-holder">
                <div className="input-holder">
                    <h3>Nome da região:</h3>
                    <input type="text" />
                </div>
                <div className="input-holder">
                    <h3>Valor regiao:</h3>
                    <input type="text" />
                </div>
                <div className="input-holder">
                    <h3>Data:</h3>
                    <input type="date" />
                </div>
                <div className="button-holder">
                    <button>Cadastrar</button>
                </div>
            </div>
        </div>
    );
}

export default CadastroRegiao;
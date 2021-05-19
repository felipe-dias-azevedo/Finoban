
function CadastroAcesso() {
    return (
        <div id="cadastro">
            <section className="title-site">
                <h1>Cadastro Acesso</h1>
            </section>
            <div className="form-holder">
                <div className="input-holder">
                    <h3>Renda:</h3>
                    <input type="text" />
                </div>
                <div className="input-holder">
                    <h3>Valor Imóvel:</h3>
                    <input type="text" />
                </div>
                <div className="input-holder">
                    <h3>Tempo Financiamento:</h3>
                    <input type="text" />
                </div>
                <div className="input-holder">
                    <h3>Porcentagem Renda:</h3>
                    <input type="text" />
                </div>
                <div className="input-holder">
                    <h3>Banco Escolhido:</h3>
                    <input type="text" />
                </div>
                <div className="input-holder">
                    <h3>Data Hora Entrada:</h3>
                    <input type="datetime-local" />
                </div>
                <div className="input-holder">
                    <h3>Data Hora Saida:</h3>
                    <input type="datetime-local" />
                </div>
                <div className="input-holder">
                    <h3>Status Saida:</h3>
                    <input type="text" />
                </div>
                <div className="input-holder">
                    <h3>Pagina Saida:</h3>
                    <input type="text" />
                </div>
                <div className="input-holder">
                    <h3>Regiao:</h3>
                    <input type="text" />
                </div>
                <div className="input-holder">
                    <h3>Cliente:</h3>
                    <input type="text" />
                </div>
                <div className="button-holder">
                    <button>Cadastrar</button>
                </div>
            </div>
        </div>
    );
}

export default CadastroAcesso;
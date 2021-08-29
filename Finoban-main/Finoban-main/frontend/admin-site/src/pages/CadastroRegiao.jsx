import { useState } from "react";
import api from '../services/api'

function CadastroRegiao() {

    const [regiao, setRegiao] = useState({
        descricaoRegiao: "",
        valorRegiao: 0,
        dataCraw: ""
    });

    function definir(e) {
        const newRegiao = { ...regiao };
        newRegiao[e.target.id] = e.target.value;
        setRegiao(newRegiao);
    }

    function registrar() {

        api.post("/regioes", regiao).then((res) => {
            alert("Região registrada");
        }).catch((err => {
            alert("Erro no cadastro");
            console.log(err);
        }));

    }
    
    return (
        <div id="cadastro">
            <section className="title-site">
                <h1>Cadastro Região</h1>
            </section>
            <div className="form-holder">
                <div className="input-holder">
                    <h3>Nome da região:</h3>
                    <input onChange={definir} type="text" id="descricaoRegiao" />
                </div>
                <div className="input-holder">
                    <h3>Valor regiao:</h3>
                    <input onChange={definir} type="number" id="valorRegiao" />
                </div>
                <div className="input-holder">
                    <h3>Data:</h3>
                    <input onChange={definir} type="datetime-local" id="dataCraw" />
                </div>
                <div className="button-holder">
                    <button onClick={registrar}>Cadastrar</button>
                </div>
            </div>
        </div>
    );
}

export default CadastroRegiao;
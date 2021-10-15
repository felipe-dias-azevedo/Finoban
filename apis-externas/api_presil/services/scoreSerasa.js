var fetch = require('cross-fetch');

async function consulta(cpf) {

    let valor = 0;

    let body = {
        CPF: cpf
    }

    const opts = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(body)
    }

    await fetch("http://localhost:8082/consultas/v1/score", opts)
        .then(async (res) => {
            if (res.ok) {
                await res.json().then(async (resposta) => {
                    if (resposta.ok) {
                        valor = resposta.data.score;
                    } else {
                        valor = 501;
                    }
                })
            }
        })
        .catch(() => {
            console.warn("Erro ao requisitar API do Serasa, usando taxa fixa.");
            valor = 501;
        });

    return valor;

}

module.exports = consulta;
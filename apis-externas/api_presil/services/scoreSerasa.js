var fetch = require('cross-fetch');

async function consulta(cnpj) {

    let valor = "Data";

    let body = {
        cnpj: cnpj
    }

    const opts = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(body)
    }

    await fetch("http://localhost:8082/consultas/v1/score", opts).then(async (res) => {
        if (res.ok) {
            await res.json().then(async (resposta) => {
                if (resposta.ok) {
                    valor = resposta.data.score;
                } else {
                    return 51;
                }
            })
        }
    });

    return valor;

}

module.exports = consulta;
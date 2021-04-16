var fetch = require('cross-fetch');

async function consulta(cnpj) {

    let b = "Data";

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

    // Número da porta provisorio
    await fetch("http://localhost:8082/consultas/v1/score", opts).then(async (res) => {
        if (res.ok) {
            await res.json().then(async (resposta) => {
                if (resposta.ok) {
                    // console.log(resposta.data.score);
                    b = resposta.data.score;
                } else {
                    return 51;
                }
            })
        }
    });

    return b;

}

// var a = consulta(123);

// console.log(a);

// a.then(res => {
//     console.log(res);
// })

module.exports = consulta;
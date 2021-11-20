const express = require('express');
const router = express.Router();
const bd = require("../bd/contas");

// Rotas
router.get('/health-check', async (_, res) => {
    const bdOk = await bd.isConnect().catch((err) => {
        console.log(err);
        return [{ ok: 0 }];
    });

    let status = 400;

    if (bdOk[0].ok > 0) {
        status = 200;
    }

    const hc = status === 200 ? "OK" : "NOK";

    res.status(status).send({
        statusHealthCheck: hc
    });
});

router.post('/clientes', async (req, res) => {
    let status = 201;

    const result = await bd.insertClient(req.body)
        .then(() => {
            return undefined;
        })
        .catch(err => {
            status = 500;
            return resposta = {
                mensagem: "Erro para se conectar ao banco",
                erro: err
            };
        });

    res.status(status).send(result);
});

router.get('/clientes', async (_, res) => {
    let status = 200;

    const clientes = await bd.selectAllClient()
        .catch(err => {
            status = 500;
            return {
                mensagem: "Erro para se conectar ao banco",
                erro: err
            }
        });

    res.status(status).send(clientes);
});

router.get('/clientes/:cpf', async (req, res) => {
    let status = 200;


    const cliente = await bd.findClient(req.params.cpf)
        .catch(err => {
            status = 500;
            return {
                mensagem: "Erro para se conectar ao banco",
                erro: err
            }
        });

    res.status(status).send(cliente);
});

router.patch('/clientes/:cpf', async (req, res) => {
    let status = 202;
    const clienteAtt = await bd.updateClient(req.params.cpf, req.body)
        .then(() => {
            return undefined;
        })
        .catch(err => {
            status = 500;
            return {
                mensagem: "Erro para se conectar ao banco",
                erro: err
            };
        });

    res.status(status).send(clienteAtt);
});

router.delete('/clientes/:cpf', async (req, res) => {
    let status = 200;
    const cliente = await bd.deleteClient(req.params.cpf)
    .then(() => {
        return undefined;
    })
    .catch(err => {
        status = 500;
        return {
            mensagem: "Erro para se conectar ao banco",
            erro: err
        };
    });

    res.status(status).send(cliente);
});

module.exports = router;
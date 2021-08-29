const express = require('express');
const router = express.Router();
const bd = require("../bd/contas");

// Rotas
router.post('/clientes', async (req, res) => {
    const result = await bd.insertClient(req.body);
    res.status(201).json({result});
});

router.get('/clientes', async (req, res) => {
    const clientes = await bd.selectAllClient();
    res.status(200).send(clientes); 
});

router.get('/clientes/:cnpj', async (req, res) => {
    const cliente = await bd.findClient(req.params.cnpj);
    res.status(200).send(cliente[0]); 
});

router.patch('/clientes/:cnpj', async (req, res) => {
    const clienteAtt = await bd.updateClient(req.params.cnpj, req.body);
    res.sendStatus(202).send(clienteAtt); 
});

router.delete('/clientes/:cnpj', async (req, res) => {
    const cliente = await bd.deleteClient(req.params.cnpj);
    res.sendStatus(200).send(cliente); 
});

module.exports = router;
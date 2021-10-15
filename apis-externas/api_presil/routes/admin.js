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

router.get('/clientes/:cpf', async (req, res) => {
    const cliente = await bd.findClient(req.params.cpf);
    res.status(200).send(cliente[0]); 
});

router.patch('/clientes/:cpf', async (req, res) => {
    const clienteAtt = await bd.updateClient(req.params.cpf, req.body);
    res.sendStatus(202).send(clienteAtt); 
});

router.delete('/clientes/:cpf', async (req, res) => {
    const cliente = await bd.deleteClient(req.params.cpf);
    res.sendStatus(200).send(cliente); 
});

module.exports = router;
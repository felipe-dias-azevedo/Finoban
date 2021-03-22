const express = require('express');
const router = express.Router();
const bd = require("../bd/contas");

// Rotas
router.get('/', (req, res) => {
  res.send('GET request to the homepage')
})

router.post('/conta', async (req, res) => {
    const conta = await bd.findClient(req.body.cnpj);
    let possui_conta = conta.length ? true : false;

    res.status(200).json({
        ok: true, 
        status: 200,
        data: {
            banco: "Banco do Presil",
            possui_conta: possui_conta
        }
    }); 

});

router.post('/financiamento', async (req, res) => {
    let dados = req.body;
    let cliente = await bd.findClient(dados.cnpj);
    let patrimonio = cliente[0].patrimonio;
    let idade = parseInt(((Date.now() - new Date(cliente[0].DataNascimento).getTime())/60000)/525600);
    let taxa;
    let taxaA;
    let dfi = 0.01;
    let mip = 0.003;

    if (patrimonio <= 5000) {
        taxa = 1.35
    } else if (patrimonio <= 13000) {
        taxa = 1.55
    } else if (patrimonio <= 20000) {
        taxa = 2.3
    } else {
        taxa = 3.0
    }

    if (idade <= 20) {
        taxaA = 3.0;
    } else if (idade <= 24) {
        taxaA = 2.4;
    } else if (idade <= 45) {
        taxaA = 1.8;
    } else if (idade <= 60) {
        taxaA = 2.4;
    } else {
        taxaA = 3.0;
    }

    res.status(200).json({
        ok: true, 
        status: 200,
        data: {
            taxa: taxa,
            taxaAdministracao: taxaA,
            dfi: dfi,
            mip: mip,
            taxaTotal: parseFloat((taxa + taxaA + dfi + mip).toFixed(2))
        }
    }); 

});

module.exports = router;
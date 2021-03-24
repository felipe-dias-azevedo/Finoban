const express = require('express');
const router = express.Router();
const bd = require('../bd/contas');
const calc = require('../libraries/calc');
const validar = require('../libraries/validar');

// Rotas
router.get('/', (req, res) => {
  res.send('API Openbancking');
})

router.post('/conta', async (req, res) => {
    let cnpj = req.body.cnpj;
    // validar.cnpj(cnpj);
    const conta = await bd.findClient(cnpj);
    let possui_conta = conta.length > 0 ? true : false;
    let status = conta.length > 0 ? 200 : 404;

    res.status(status).json({
        ok: true, 
        status: status,
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
    let txa = calc(patrimonio, idade);
    let dfi = 0.01;
    let mip = 0.003;

    res.status(200).json({
        ok: true, 
        status: 200,
        data: {
            taxa: txa.taxa,
            taxaAdministracao: txa.taxaA,
            dfi: dfi,
            mip: mip,
            taxaTotal: parseFloat((txa.taxa + txa.taxaA + dfi + mip).toFixed(2))
        }
    }); 

});

module.exports = router;
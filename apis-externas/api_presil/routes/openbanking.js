const express = require('express');
const router = express.Router();
const bd = require('../bd/contas');
const calc = require('../services/calc');
const validar = require('../services/validar');
const response = require('../services/response');

// Rotas
router.get('/', (req, res) => {
    res.send('API Openbancking');
})

router.post('/conta', async (req, res) => {
    let cnpj = req.body.cnpj;
    let v_body = validar.cnpj(cnpj);

    let status;
    let data;

    if (v_body) {

        const conta = await bd.findClient(cnpj);
        let possui_conta = conta.length > 0 ? true : false;
        status = conta.length > 0 ? 200 : 404;
        data = {
            banco: "Banco do Presil",
            possui_conta: possui_conta
        }

    } else {

        status = 400;
        data = {
            erro: "CNPJ inválido"
        }

    }
    
    res.status(status).json(response(status, data));

});

router.post('/financiamento', async (req, res) => {
    let dados = req.body;
    let v_dados = validar.financiamento(dados);

    let dfi = 0.01;
    let mip = 0.003;
    let data;
    let status;
    let erros = [];

    if (v_dados.valido) {

        let cliente = await bd.findClient(dados.cnpj);

        if (cliente[0]) {
            status = 200;
            let patrimonio = cliente[0].patrimonio;
            let idade = parseInt(((Date.now() - new Date(cliente[0].DataNascimento).getTime()) / 60000) / 525600);
            let txa = calc(patrimonio, idade);
            let tx_adm = 0.5;
            data = {
                taxa: parseFloat(txa.toFixed(2)),
                taxaAdministracao: tx_adm,
                dfi: dfi,
                mip: mip,
                taxaTotal: parseFloat((txa + tx_adm + dfi + mip).toFixed(2))
            }
        } else {
            status = 404;
            data = {
                erro: "Cliente não encontrado na base de dados"
            }
        }

    } else {

        status = 400;
        v_dados.erros.forEach(erro => {
            erros.push(erro);
        });
        data = {
            erro: erros
        }

    }

    res.status(status).json(response(status, data));

});

module.exports = router;
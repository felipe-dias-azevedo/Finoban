const express = require('express');
const router = express.Router();
const bd = require('../bd/contas');
const calc = require('../services/calc');
const validar = require('../services/validar');
const response = require('../services/response');
const taxas_fixas = require('../services/taxas_fixas.json');

// Rotas
router.get('/', (_, res) => {
    res.send('API Openbancking');
})

router.post('/conta', async (req, res) => {
    let cpf = req.body.cpf;
    let v_body = validar.cpf(cpf);

    let status;
    let data;

    if (v_body) {

        await bd.findClient(cpf)
            .then((res) => {
                let possui_conta = res.length > 0 ? true : false;
                status = possui_conta ? 200 : 404;
                data = {
                    banco: "Banco do Presil",
                    possui_conta: possui_conta
                }
            })
            .catch(err => {
                status = 500;
                data = {
                    mensagem: "Erro para se conectar ao banco",
                    erro: err
                }
            });

    } else {
        status = 400;
        data = {
            erro: "CPF inválido"
        }
    }

    res.status(status).json(response(status, data));

});

router.post('/financiamento', async (req, res) => {
    let dados = req.body;
    let v_dados = validar.financiamento(dados);
    dados.cpf = Number(dados.cpf);

    let data;
    let status;
    let erros = [];

    if (v_dados.valido) {

        await bd.findClient(dados.cpf)
            .then(async res => {
                if (res[0]) {
                    status = 200;
                    let c = res[0];
                    let patrimonio = c.patrimonio;
                    let renda = dados.renda;
                    let tempo_f = dados.tempoFinanciamento;
                    let valor_imovel = dados.valorImovel;
                    let idade = parseInt(((Date.now() - new Date(res[0].DataNascimento).getTime()) / 60000) / 525600);
                    let txa = await calc(patrimonio, idade, renda, tempo_f, valor_imovel, dados.cpf);
                    data = {
                        taxa: parseFloat(txa.toFixed(2)),
                        taxaAdministracao: taxas_fixas.tx_adm,
                        dfi: taxas_fixas.dfi,
                        mip: taxas_fixas.mip,
                        taxaTotal: parseFloat((txa + taxas_fixas.tx_adm + taxas_fixas.dfi + taxas_fixas.mip))
                    }
                } else {
                    status = 404;
                    data = {
                        erro: "Cliente não encontrado na base de dados"
                    }
                }
            })
            .catch(err => {
                status = 500;
                data = {
                    mensagem: "Erro para se conectar ao banco",
                    erro: err
                }
            });

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
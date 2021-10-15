const serasa = require('./scoreSerasa');

async function calcTxa(patrimonio, idade, renda, tempo_f, valor_imovel, cpf) {

    let taxa_patrimonio = 0;
    let taxa_idade = 0;
    let taxa_renda = 0;
    let taxa_tempo = 0;
    let taxa_imovel = 0;
    let taxa_serasa = serasa(cpf);
    let taxa_serasa2 = 1000;

    await taxa_serasa
        .then(res => {
            taxa_serasa2 = (taxa_serasa2 - res) * 0.000007;
            taxa_serasa = res;
        })

    // Tx Patrimonio 
    taxa_patrimonio = (patrimonio / 100000) * 0.0005;

    // Tx Renda 
    taxa_renda = (renda / 10000) * 0.007;

    // Tx Imovel
    taxa_imovel = (valor_imovel / 100000) * 0.0012;

    // Tx Tempo de financiamento 
    taxa_tempo = tempo_f * 0.00075;

    // Calc Tx Idade
    let idade_ideal = 25;
    let tx_min = 0.02;
    let coef_idade = (idade / idade_ideal);
    taxa_idade = (Math.abs(1 - coef_idade) * 0.03) + tx_min;

    // Calc Total
    let taxa_total = taxa_patrimonio + taxa_idade + taxa_renda + taxa_tempo + taxa_imovel + taxa_serasa2;

    return taxa_total;

};

module.exports = calcTxa;
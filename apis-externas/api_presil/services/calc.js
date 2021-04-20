const serasa = require('./scoreSerasa');

async function calcTxa(patrimonio, idade, renda, tempo_f, valor_imovel, cnpj) {
    
    let taxa_patrimonio = 0;
    let taxa_idade = 0;
    let taxa_renda = 0;
    let taxa_tempo = 0;
    let taxa_imovel = 0;
    let taxa_serasa = serasa(cnpj);
    let taxa_serasa2 = 1000;

    await taxa_serasa.then(res => {
        taxa_serasa2 = (taxa_serasa2 - res) * 0.00005;
        taxa_serasa = res;
    })
    
    // Tx Patrimonio 
    taxa_patrimonio = patrimonio * 0.00000003 + 0.02;
    
    // Tx Renda 
    taxa_renda = renda * 0.0000003 + 0.03;
    
    // Tx Imovel
    taxa_imovel = valor_imovel * 0.000000025;
    
    // Tx Tempo de financiamento 
    taxa_tempo = tempo_f * 0.001;
    
    // Calc Tx Idade
    let idade_ideal = 25;
    let tx_min = 0.02;
    let coef_idade = (idade / idade_ideal);
    taxa_idade = (Math.abs(1 - coef_idade) * 0.03) + tx_min;
    
    // Calc Total
    let taxa_total = taxa_patrimonio + taxa_idade + taxa_renda + taxa_tempo + taxa_imovel + taxa_serasa2;

    console.log("Tx Patrimonio: " + taxa_patrimonio);
    console.log("Tx Idade: " + taxa_idade);
    console.log("Tx Renda: " + taxa_renda);
    console.log("Tx Tempo: " + taxa_tempo);
    console.log("Tx Imovel: " + taxa_imovel);
    console.log("Tx Serasa: " + taxa_serasa2);
    console.log("Tx Total: " + taxa_total);
    
    return taxa_total;

};

module.exports = calcTxa;
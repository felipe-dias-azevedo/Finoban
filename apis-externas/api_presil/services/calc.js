let calcTxa = (patrimonio, idade, renda, tempo_f, valor_imovel) => {
    
    let taxa_patrimonio = 0;
    let taxa_idade = 0;
    let taxa_renda = 0;
    let taxa_tempo = 0;
    let taxa_imovel = 0;
    let taxa_serasa = (100 - parseInt(Math.random() * 100)) * 0.05;
    
    // Calc Tx Patrimonio 
    taxa_patrimonio = patrimonio * 0.000015;
    
    // Calc Tx Renda 
    taxa_renda = renda * 0.0001;
    
    // Calc Tx Imovel
    taxa_imovel = valor_imovel * 0.000005;
    
    // Calc Tx Tempo de financiamento 
    taxa_tempo = tempo_f * 0.1;
    
    // Calc Tx Idade
    let idade_ideal = 25;
    let tx_min = 3.0;
    let coef_idade = (idade / idade_ideal);
    taxa_idade = (2 * Math.abs(1 - coef_idade)) + tx_min - (1 - coef_idade);
    
    // Calc Total
    let taxa_total = taxa_patrimonio + taxa_idade + taxa_renda + taxa_tempo + taxa_imovel + taxa_serasa;
    
    return taxa_total;

};

module.exports = calcTxa;
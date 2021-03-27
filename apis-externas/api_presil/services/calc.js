let calcTxa = (patrimonio, idade) => {
    
    let taxa_p = 0;
    let taxa_i = 0;
    
    // Calc Tx Patrimonio 
    if (patrimonio <= 5000) {
        taxa_p = 1.35;
    } else if (patrimonio <= 13000) {
        taxa_p = 1.55;
    } else if (patrimonio <= 20000) {
        taxa_p = 2.3;
    } else {
        taxa_p = 3.0;
    }
    
    // Calc Tx Idade
    let idade_ideal = 25;
    let tx_min = 3.0;
    let coef_idade = (idade / idade_ideal);
    taxa_i = (2 * Math.abs(1 - coef_idade)) + tx_min - (1 - coef_idade);
    
    // Calc Total
    let taxa_total = taxa_p + taxa_i;
    
    return taxa_total;

};

module.exports = calcTxa;
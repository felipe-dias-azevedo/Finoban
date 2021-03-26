let calcTxa = (patrimonio, idade) => {

    let taxa = 0;
    let taxaA = 0;

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

    return {
        taxa: taxa,
        taxaA: taxaA
    }

};

module.exports = calcTxa;
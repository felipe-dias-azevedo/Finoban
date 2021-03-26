let validar_cnpj = (cnpj) => {

    if (isNaN(Number(cnpj))) {
        return false;    
    }

    return true;

};

let validar_financiamento = (dados) => {

    let body = dados;
    let valido = true;
    let erros = []

    if (body.cnpj == undefined || isNaN(Number(body.cnpj))) {
        valido = false;
        erros.push("CNPJ inválido");
    }

    if (body.valorImovel == undefined || isNaN(Number(body.valorImovel))) {
        valido = false;
        erros.push("Valor do imovel inválido");
    }

    if (body.tempoFinanciamento == undefined || isNaN(Number(body.tempoFinanciamento))) {
        valido = false;
        erros.push("Tempo de financiamento inválido");
    }

    return {
        valido: valido,
        erros: erros
    };

};

module.exports = {
    cnpj: validar_cnpj,
    financiamento: validar_financiamento
};
let validar_cpf = (cpf) => {

    if (isNaN(Number(cpf))) {
        return false;    
    }

    return true;

};

let validar_financiamento = (dados) => {

    let body = dados;
    let valido = true;
    let erros = []

    if (body.cpf == undefined || isNaN(Number(body.cpf))) {
        valido = false;
        erros.push("CPF inválido");
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
    cpf: validar_cpf,
    financiamento: validar_financiamento
};
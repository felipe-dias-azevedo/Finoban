function numInvalido(num) {
    if (num == undefined) {
        return true;
    }

    if (isNaN(Number(num))) {
        return true;
    }

    if (Number(num) == 0) {
        return true;
    }

    if (num == "") {
        return true;
    }

    return false;
};

let validar_cpf = (cpf) => {

    if (numInvalido(cpf)) {
        return false;
    }

    return true;

};

let validar_financiamento = (dados) => {

    let body = dados;
    let valido = true;
    let erros = [];

    if (numInvalido(body.cpf)) {
        valido = false;
        erros.push("CPF inválido");
    }

    if (numInvalido(body.valorImovel)) {
        valido = false;
        erros.push("Valor do imovel inválido");
    }

    if (numInvalido(body.tempoFinanciamento)) {
        valido = false;
        erros.push("Tempo de financiamento inválido");
    }

    if (numInvalido(body.renda)) {
        valido = false;
        erros.push("Valor de renda inválido");
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
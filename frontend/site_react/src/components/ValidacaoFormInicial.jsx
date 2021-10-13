export default function validate(values) {

	let errors = {};
	if (!values.cpf) {
		errors.cpf = "Insira um CPF";
	}

	if (!values.renda) {
		errors.renda = "Insira sua renda mensal";
        console.log(values.renda);
	}

	if (!values.valorImovel) {
		errors.valorImovel = "Insira o valor do imóvel";
	} 

	if (!values.tempoFinanciamento) {
		errors.tempoFinanciamento = "Insira o tempo do financiamento";
	} 
	if (!values.porcentagemRenda) {
		errors.porcentagemRenda = "Insira a porcentagem de sua renda";
	} else if (values.porcentagemRenda > 100) {
		errors.porcentagemRenda =
			"Insira uma porcentagem de renda de entre 0 a 100";
	}

	return errors;
}

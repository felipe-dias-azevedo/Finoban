function validarCNPJ(cnpj) {
	cnpj = cnpj.replace(/[^\d]+/g, "");

	if (cnpj == "") return false;

	if (cnpj.length != 14) return false;

	// Elimina CNPJs invalidos conhecidos
	if (
		cnpj == "00000000000000" ||
		cnpj == "11111111111111" ||
		cnpj == "22222222222222" ||
		cnpj == "33333333333333" ||
		cnpj == "44444444444444" ||
		cnpj == "55555555555555" ||
		cnpj == "66666666666666" ||
		cnpj == "77777777777777" ||
		cnpj == "88888888888888" ||
		cnpj == "99999999999999"
	)
		return false;

	// Valida DVs
	var tamanho, numeros, digitos, soma, pos, resultado;
	tamanho = cnpj.length - 2;
	numeros = cnpj.substring(0, tamanho);
	digitos = cnpj.substring(tamanho);
	soma = 0;
	pos = tamanho - 7;
	for (var i = tamanho; i >= 1; i--) {
		soma += numeros.charAt(tamanho - i) * pos--;
		if (pos < 2) pos = 9;
	}
	resultado = soma % 11 < 2 ? 0 : 11 - (soma % 11);
	if (resultado != digitos.charAt(0)) return false;

	tamanho = tamanho + 1;
	numeros = cnpj.substring(0, tamanho);
	soma = 0;
	pos = tamanho - 7;
	for (var i = tamanho; i >= 1; i--) {
		soma += numeros.charAt(tamanho - i) * pos--;
		if (pos < 2) pos = 9;
	}
	resultado = soma % 11 < 2 ? 0 : 11 - (soma % 11);
	if (resultado != digitos.charAt(1)) return false;

	return true;
}

export default function validate(values) {
	let errors = {};
	if (!values.nome) {
		errors.nome = "Insira seu nome completo";
	} else if (values.nome.length < 3) {
		errors.nome = "Nome inválido";
	}

	if (!values.cnpj) {
		errors.cnpj = "Insira um CNPJ";
	} else if (values.cnpj) {
		if (!validarCNPJ(values.cnpj)) {
			errors.cnpj = "CNPJ inválido!";
		}
	}

	if (!values.email) {
		errors.email = "Insira um endereço de e-mail válido";
	} else if (!/\S+@\S+\.\S+/.test(values.email)) {
		errors.email = "Endereço de e-mail inválido!";
	}

	if (!values.senha) {
		errors.senha = "Insira uma senha";
	} else if (values.senha.length < 6) {
		errors.senha = "Insira uma senha com no mínimo 6 caracteres";
	}

	if (!values.confirmarSenha) {
		errors.confirmarSenha = "Insira uma senha";
	} else if (values.confirmarSenha.length < 6) {
		errors.confirmarSenha = "Insira uma senha com no mínimo 6 caracteres";
	} else if (values.senha != values.confirmarSenha) {
		errors.confirmarSenha = "As duas senhas precisam ser iguais";
	}

	return errors;
}

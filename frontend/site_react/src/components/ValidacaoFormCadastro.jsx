function validarCpf(cpf) {
	cpf = cpf.replace(/\D/g, "");
	if (cpf.toString().length != 11 || /^(\d)\1{10}$/.test(cpf)) {
		return false;
	}

	var resultado = true;

	[9, 10].forEach(function (j) {
		var soma = 0,
			r;
		cpf.split(/(?=)/)
			.splice(0, j)
			.forEach(function (e, i) {
				soma += parseInt(e) * (j + 2 - (i + 1));
			});
		r = soma % 11;
		r = r < 2 ? 0 : 11 - r;
		if (r != cpf.substring(j, j + 1)) resultado = false;
	});

	return resultado;
}

export default function validate(values) {
	console.log(values);
	let errors = {};
	if (!values.nome) {
		errors.nome = "Insira seu nome completo";
	}
	if (typeof values.nome != "string") {
		errors.nome = "Insira um nome válido";
	} else if (typeof values.nome == "string" && values.nome.length < 5) {
		errors.nome = "Insira um nome válido";
	}

	if (!values.cpf) {
		errors.cpf = "Insira um CPF";
	} else if (values.cpf) {
		if (!validarCpf(values.cpf)) {
			errors.cpf = "Insira um CPF válido";
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

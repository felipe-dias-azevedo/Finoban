var express = require('express');
var axios = require('axios');
var router = express.Router();

let sessoes = [];

/* Recuperar usuário por login e senha */
router.post('/autenticar', function(req, res) {
	console.log('Recuperando usuário por login e senha');

	var email = req.body.email; // depois de .body, use o nome (name) do campo em seu formulário de login
	var senha = req.body.senha; // depois de .body, use o nome (name) do campo em seu formulário de login

    console.log('email: ' + email + ' senha: ' + senha);

    axios.post(`http://localhost:8080/api-finoban/login?email=${email}&senha=${senha}`).then(response => {
        var resultados = response.data;
        var resultado = [];
        for (let i = 0; i < resultados.length; i++) {
            if (resultados[i].email == login && resultados[i].senha == senha) {
                resultado.push(resultados[i]);
            }
        }
        if (resultado.length == 0) {
            resultado = resultados;
        }

		resultado = [resultado];

        console.log(`Encontrados: ${resultado.length}`);

		console.log(resultado);


		if (resultado.length == 1) {
			sessoes.push(resultado[0].email);
			console.log('sessoes: ',sessoes);
			res.json(resultado[0]);
		} else if (resultado.length == 0) {
			res.status(403).send('Login e/ou senha inválido(s)');
		} else {
			res.status(403).send('Mais de um usuário com o mesmo login e senha!');
		}
    }).catch(err => {
        console.error(err);
		res.status(500).send(err.message);
    })
});


/* Cadastrar usuário */
router.post('/cadastrar', function(req, res) {
	console.log('Criando um usuário');
	
	var nome = req.body.nome;
	var cnpj = req.body.cnpj;
    var email = req.body.email;
    var senha = req.body.senha;
	var cep = req.body.cep;
	var numeroCasa = req.body.numeroCasa;

    axios.post(`http://localhost:8080/api-finoban/cadastro?nome=${nome}&cnpj=${cnpj}&email=${email}&senha=${senha}&cep=${cep}&numeroCasa=${numeroCasa}`,{
		
	}
    ).then(response => {
        console.log(`Registro criado: ${response}`)
        res.send(true);
    }).catch(err => {
        console.error(err);
		res.status(500).send(err.message);
    })
});


/* Verificação de usuário */
router.get('/sessao/:login', function(req, res) {
	let login = req.params.login;
	console.log(`Verificando se o usuário ${login} tem sessão`);
	
	let tem_sessao = false;
	for (let u=0; u<sessoes.length; u++) {
		if (sessoes[u] == login) {
			tem_sessao = true;
			break;
		}
	}

	if (tem_sessao) {
		let mensagem = `Usuário ${login} possui sessão ativa!`;
		console.log(mensagem);
		res.send(mensagem);
	} else {
		res.sendStatus(403);
	}
	
});


/* Logoff de usuário */
router.get('/sair/:login', function(req, res) {
	let login = req.params.login;
	console.log(`Finalizando a sessão do usuário ${login}`);
	let nova_sessoes = []
	for (let u=0; u<sessoes.length; u++) {
		if (sessoes[u] != login) {
			nova_sessoes.push(sessoes[u]);
		}
	}
	sessoes = nova_sessoes;
	res.send(`Sessão do usuário ${login} finalizada com sucesso!`);
});


/* Recuperar todos os usuários */
router.get('/', function(req, res) {
	console.log('Recuperando todos os usuários');
	// Usuario.findAndCountAll().then(resultado => {
	// 	console.log(`${resultado.count} registros`);

	// 	res.json(resultado.rows);
	// }).catch(erro => {
	// 	console.error(erro);
	// 	res.status(500).send(erro.message);
  	// });
});

module.exports = router;

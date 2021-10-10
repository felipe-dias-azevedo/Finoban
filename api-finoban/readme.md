<h1>Documentação API Finoban</h1>

### Tratamento de Exceções (Handle Exception)

```ruby
FIN001 - "Acesso não Encontrado."
FIN002 - "Cliente não encontrado."
FIN003 - "Região não encontrada."
FIN004 - "Avaliação não encontrado."
FIN005 - "avalPositivoEnum Incorreto."
FIN006 - "feedbackAval não pode ser null"
FIN007 - "Email não encontrado."
FIN008 - "Senha incorreta."
FIN009 - "Usuário já logado."
FIN010 - "Usuário não logado."
FIN011 - "usuário já cadastrado."
FIN012 - "Erro ao enviar email."
FIN013 - "Token expirado."
FIN014 - "Token Inválido."
FIN015 - "Permissão não encontrada."
FIN016 - "Admin não encontrado."
FIN017 - "Cargo não existente."
```

### Testes Integrados
<img src="https://img.shields.io/static/v1?label=Testes Integrados&message=CUCUMBER&color=blue&style=for-the-badge&logo=CUCUMBER"/>

<br>
<br>

```
padrão: Gherkin - BDD (Behavior Driven Development)
```

Exemplo de padrão gherkin
```feature
Feature: Gestão de operações matemáticas

Scenario: Soma de dois números
  Given Que tenho um número 72
  And Outro número 55
  When Somar os dois
  Then Deverá retornar 127
```
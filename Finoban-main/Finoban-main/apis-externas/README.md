# Documentação API

## Requisitos API
* #### Conexão com Banco de Dados (Sugestão: SQLite)
  ##### Simulando um cenário real dos Bancos
* #### Padrão nos Endpoints
* #### Analise de Perfil do Cliente
  
## Requisitos Banco de Dados da API
### Objetivo: Armazenar os clientes do banco
## Tabela:
* ### Cliente
## Atributos:
* CNPJ (PK) (INTEGER)
* Nome (VARCHAR)
* Patrimonio (DOUBLE)
* DataNascimento (DATE)

# Dados padrão para o Banco

```
insert into Cliente values 
(123456789000, "Carlos Roberto", 50000.0, 1985-12-16),
(12345, "João da Silva", 10000.0, 1993-05-24),
(123, "Silvio Santos",  1000000.0, 1970-01-01),
(456, "Gabriel Rodrigues",  50000.0, 1995-03-27),
(789, "Fernando Brandão",  10000.0, 1998-06-14);
```

# Público Alvo Bancos

* ## Cifra: Pessoas de alta renda que não são jovens.
* ## Presil: Pessoas de baixa e/ou media renda que não são jovens.
* ## S16: Pessoas de baixa, media, alta renda de jovens.

# Duas rotas

## Rota Taxa Financiamento

```POST http://localhost:XXXX/openbanking/v1/financiamento ```

### Body Requisição:
```
{
    "cnpj": 548762565555560,
    "valorImovel": 400000,
    "tempoFinanciamento": 30
}
```

``` cnpj: tipo int recebe o número do CNPJ sem ".", "/" e "-"```

``` valorImovel: tipo int recebe o valor total do imóvel ```

``` tempoFinanciamento: tipo int recebe a quantidade de anos em que o imóvel será financiado ```

### Calculo da API para obter valores das taxas desta Rota

* Cifra: Reta decrescente onde quanto maior o valor do Patrimônio e maior idade a taxa diminui (parabola com idade)
* Presil: Reta ligeiramente crescente onde quanto menor o valor do Patrimônio e maior idade a taxa diminui (parabola com idade)
* S16: Reta ligeiramente decrescente onde quanto maior o valor do Patrimônio e menor idade a taxa diminui (parabola com idade)

### Body Resposta:
```
{
    "ok": true,
    "status": 200,
    "data": {
        "taxa": 0.65
        "taxaAdministracao": 0.4
        "dfi": 0.01
        "mip": 0.003
        "taxaTotal": 0.7,
    }
}
```

``` ok: tipo boolean retorna se a requisição foi feita com sucesso (sem erros) ```

``` status: tipo integer retorna o código do Status HTTP ```

``` taxaAdministracao: tipo double (float) retorna o valor da taxa de administração do banco ```

``` dfi: tipo double (float) retorna o valor da taxa do seguro ```

``` mip: tipo double (float) retorna o valor da taxa do seguro ```

``` taxaTotal: tipo double (float) retorna o valor da taxa total para o financiamento ```

## Rota verificar se possui conta no Banco

```POST http://localhost:XXXX/openbanking/v1/conta ```

### Body Requisição:
```
{ 
    "cnpj": 548762565555560
}
```

``` cnpj: tipo int recebe o número do CNPJ sem ".", "/" e "-"```

### Body Resposta:
```
{
    "ok": true,
    "status": 200,
    "data": {
		"banco": "S16 Bank",
		"possuiConta": true
    }
}
```

``` ok: tipo boolean retorna se a requisição foi feita com sucesso (sem erros) ```

``` status: tipo integer retorna o código do Status HTTP ```

``` banco: tipo string retorna o nome do banco ```

``` possuiConta: tipo boolean retorna se o usuário possui conta no banco. ```

# API Serasa

## Rota Única

```POST http://localhost:XXXX/consultas/v1/score ```

### Body Requisição:
```
{ 
    "cnpj": 548762565555560
}
```

``` cnpj: tipo int recebe o número do CNPJ sem ".", "/" e "-"```

### Body Resposta:
```
{
    "ok": true,
    "status": 200,
    "data": {
        "score": 775
    }
}
```

``` score: tipo int retorna o valor do score (número entre 0 e 999)```
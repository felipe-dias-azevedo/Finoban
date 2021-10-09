<h1 align="left">
    🔗 Finoban - Financing Open Banking
</h1>
<img src="https://img.shields.io/static/v1?label=Project&message=FINOBAN&color=blue&style=for-the-badge&logo=ghost"/>
<p align="left">🚀 Projeto com foco em aprimorar a experiência com financiamentos imobiliários</p>

Grupo 09 - 3CCOA

## Contextualização
TBD

## Arquitetura

### V1

<p align="center">
  <img width="800" src="./.github/architecture.png">
</p>

### V2

<p align="center">
  <img width="800" src="./.github/architecture-v2.png">
</p>


## Instalação

### Dependências

* java
* maven
* nodejs
* npm
* python 3
* pip
* .NET Core
* Golang
* Docker
* MySQL
* R
  
<br>

### Backend

```
  sudo apt install maven
  cd api-finoban/finoban/
  mvn spring-boot:run
```

### Para visualizar a documentação da API

```ruby
url: "http://localhost:8080/swagger-ui.html"
```

### Frontend

```
  sudo apt install nodejs npm
  cd frontend/site-react/
  npm i
  npm start
```

### Scraper

```
  cd crawler/
  pip3 install -r requirements.txt
  python3 main.py
```

### APIs externas

#### Instalando Dependências

```
  cd scripts_shell/install/
  source ./apis.sh
```

#### <b>API Serasa:</b> Golang

```
  cd scripts_shell/start/apis/
  source ./iniciar_serasa.sh
```

#### <b>API S16 Bank:</b> Python Flask

```
  cd scripts_shell/start/apis/
  source ./iniciar_s16.sh
```

#### <b>API Banco Cifra:</b> .NET Core

```
  cd scripts_shell/start/apis/
  source ./iniciar_cifra.sh
```

#### <b>API Banco do Presil:</b> Nodejs

```
  cd scripts_shell/start/apis/
  source ./iniciar_presil.sh
```

### Inserir dados em massa - para MySQL

```
  cd banco_de_dados/inserir-em-massa/
  pip3 install -r requirements.txt
  python3 script.py
```

## Integrantes

* Catarina Carneiro
* Felipe Azevedo
* José Silva
* Mario Heleno
* Victor Barbosa
* Vinícius Carvalho

<!-- <h2>
  Algumas das tecnologias que mais utilizamos
</h2>

<img src="https://upload.wikimedia.org/wikipedia/commons/thumb/6/6d/.net_logo.svg/1200px-.net_logo.svg.png" style="height:50px"></img>

<img src="https://marcas-logos.net/wp-content/uploads/2020/11/Java-logo.png" style="height:100px"></img>

<img src="https://upload.wikimedia.org/wikipedia/commons/thumb/d/d9/Node.js_logo.svg/1200px-Node.js_logo.svg.png" style="height:100px"></img> -->

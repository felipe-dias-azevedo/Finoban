CREATE TABLE cliente (
    id_cliente INT PRIMARY KEY AUTO_INCREMENT,
    cnpj VARCHAR(255),
    nome VARCHAR(255),
    email VARCHAR(255),
    senha VARCHAR(255),
    cep VARCHAR(255),
    bairro VARCHAR(255),
    numero INT,
    data_nasc DATE,
    data_criacao DATETIME
);

CREATE TABLE regiao (
    id_regiao INT PRIMARY KEY AUTO_INCREMENT,
    descricao_regiao VARCHAR(255),
    valor_regiao INT,
    data_craw DATETIME
);

CREATE TABLE acesso (
    id_entrada INT PRIMARY KEY AUTO_INCREMENT,
    data_hora_entrada DATETIME,
    data_hora_saida DATETIME,
    status_saida INT,
    pagina_saida INT,
    renda DECIMAL(14,2),
    valor_imovel DECIMAL(14,2),
    tempo_financiamento INT,
    porcentagem_renda INT,
    banco_escolhido INT,
    fk_regiao INT,
    fk_cliente INT,
    FOREIGN KEY (fk_regiao) REFERENCES regiao (id_regiao),
    FOREIGN KEY (fk_cliente) REFERENCES cliente (id_cliente)
);

CREATE TABLE avaliacao (
    id_avaliacao INT PRIMARY KEY AUTO_INCREMENT,
    aval_positivo INT,
    feedback_aval VARCHAR(255),
    data_aval DATETIME,
    fk_acesso INT,
    FOREIGN KEY (fk_acesso) REFERENCES acesso (id_entrada)  
);
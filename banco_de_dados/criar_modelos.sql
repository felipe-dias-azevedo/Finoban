CREATE TABLE cliente (
    id_cliente INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255),
    cnpj VARCHAR(255),
    cep VARCHAR(255),
    numero INT,
    renda DOUBLE(14,2),
    data_nasc DATE,
    data_criacao DATETIME
);

CREATE TABLE regiao (
    id_regiao INT,
    descricao_regiao VARCHAR(255),
    valor_regiao INT,
    data_craw DATETIME
);

CREATE TABLE acesso (
    id_entrada INT PRIMARY KEY AUTO_INCREMENT,
    data_hora_entrada DATETIME,
    status_saida INT,
    data_hora_saida DATETIME,
    pagina_saida INT,
    fk_regiao INT,
    fk_cliente INT
    FOREIGN KEY (fk_regiao) REFERENCES regiao (id_regiao),
    FOREIGN KEY (fk_cliente) REFERENCES cliente (id_cliente)
);

CREATE TABLE avaliacao (
    id_avaliacao INT,
    aval_positivo INT,
    feedback_aval VARCHAR(255),
    data_aval DATETIME,
    fk_acesso INT,
    FOREIGN KEY (fk_acesso) REFERENCES acesso (id_entrada)  
);
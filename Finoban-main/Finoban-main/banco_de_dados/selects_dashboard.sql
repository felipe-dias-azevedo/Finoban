
-- RENDIMENTO MENSAL (RENDA / USUARIOS)
-- fazer um for para ver cada dia do mês e então fazer uma média da renda do dia
select data_hora_saida, renda from acesso, cliente where fk_cliente = id_cliente and data_hora_saida >= date_sub(now(),interval 30 day);

-- PORCENTUAL PERDAS CLIENTES
-- ANTIGO -> fazer os dois selects e dividir o resultado de um pelo outro para obter a porcentagem
-- NOVO -> fazer somente o ultimo select e somar todos os valores da coluna da direita e dividir pela quantidade de 0 da primeira coluna
-- (dados dos ultimos 15 dias)
select count(id_cliente) from cliente, acesso where fk_cliente = id_cliente and data_hora_saida >= date_sub(now(),interval 15 day);
select count(id_cliente) from cliente, acesso where fk_cliente = id_cliente and status_saida = 0 and data_hora_saida >= date_sub(now(),interval 15 day);
select status_saida, count(*) from cliente, acesso where fk_cliente = id_cliente and data_hora_saida >= date_sub(now(),interval 15 day) group by status_saida;

-- PROJEÇÃO RENDIMENTO PROX MES
-- aplicar formula de regressão linear baseada nos valores obtidos do grafico de rendimento mensal
-- (não tem necessidade de fazer select, pode somente reutilizar o método)
select data_hora_saida, renda from acesso, cliente where fk_cliente = id_cliente and data_hora_saida >= date_sub(now(),interval 30 day);

-- HISTOGRAMA TEMPO PERMANENCIA
-- tratar dados de tempo de permanencia em intervalos de 15 segundos para facilitar visualização
select data_hora_entrada, data_hora_saida from acesso, cliente where fk_cliente = id_cliente;

-- HISTOGRAMA AVALIACAO SITE
-- retornar os valores da coluna da direita 
select aval_positivo, count(*) from avaliacao group by aval_positivo;

-- CORRELAÇÃO (REGIAO / RENDA) -> grafico scatter?
-- ???
select descricao_regiao as 'descricaoRegiao', renda from acesso, regiao where fk_regiao = id_regiao;

-- CORRELAÇÃO (VALOR IMOVEL / IDADE) -> grafico scatter?
-- ???
SELECT data_nasc as 'dataNasc', renda FROM cliente, acesso where id_cliente = fk_cliente;

-- HISTOGRAMA REGIOES ESCOLHIDAS
-- retornar os valores da coluna da direita (utilizar enum do tipo string para identificar regiao)
select fk_regiao, count(*) from acesso group by fk_regiao;

-- CORRELAÇÃO (VALOR IMOVEL / RENDA) -> grafico scatter?
-- ???
SELECT valor_imovel as 'valorImovel', renda from acesso;

-- GEOMAP (CEP / REGIAO ESCOLHIDA)
-- TBD
SELECT cep, descricao_regiao  FROM acesso, cliente, regiao where fk_cliente =id_cliente and fk_regiao = id_regiao;

-- HISTOGRAMA BANCOS ESCOLHIDOS
-- retornar os valores da coluna da direita (utilizar enum do tipo string para identificar bancos)
select banco_escolhido, count(*) from acesso group by banco_escolhido;

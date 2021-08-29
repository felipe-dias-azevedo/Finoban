import ssl
ssl._create_default_https_context = ssl._create_unverified_context
import conexao_mysql as conexao
import crawler
import time

db_host_name = "finoban.cxifhb2i4s1j.us-east-1.rds.amazonaws.com"
db_user_name = "felipe"
db_password = "123mysql"
db_database = "Finoban"

conexao = conexao.ConexaoMySQL(db_host_name,db_user_name,db_password,db_database)
imoveis = crawler.Imoveis()

while True:
    dados = imoveis.run()
    conexao.insercao(dados) #inserir dados no parametro
    print("Esperando 20 segundos para próximo Insert...\n")
    time.sleep(20)
    
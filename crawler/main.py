import ssl
ssl._create_default_https_context = ssl._create_unverified_context
import conexao_mysql as conexao
import crawler
import time

conexao = conexao.ConexaoMySQL("localhost","","","Finoban")
imoveis = crawler.Imoveis()

while True:
    dados = imoveis.run()
    conexao.insercao(dados) #inserir dados no parametro
    print("Esperando 20 segundos para próximo Insert...\n")
    time.sleep(20)
    
import ssl
ssl._create_default_https_context = ssl._create_unverified_context
import conexao_mysql as conexao
import crawler
import time

conexao = conexao.ConexaoMySQL("18.207.233.50","root","urubu100","Finoban")
imoveis = crawler.Imoveis()
dados = imoveis.run()

while True:
    conexao.insercao(dados) #inserir dados no parametro
    time.sleep(5)
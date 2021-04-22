import conexao_mysql as conexao
import time

conexao = conexao.ConexaoMySQL("18.207.233.50","root","urubu100","Finoban")

while True:
    conexao.insercao([('Região montanhosa', 40000)]) #inserir dados no parametro
    time.sleep(5)
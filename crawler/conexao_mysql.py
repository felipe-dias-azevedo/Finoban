import mysql.connector
from datetime import datetime

class ConexaoMySQL:

    def __init__(self, host, user, password, database):
        self.host = host
        self.user = user
        self.password = password
        self.database = database

    def conexao(self):
        print("\nEstabelecendo conexão com o Banco de Dados...")
        try:
            mydb = mysql.connector.connect(
                host = self.host,
                user = self.user,
                password = self.password,
                database = self.database
            )
            print("Conexão feita com sucesso!\n")
            return mydb
        except Exception as e:
            print(e)
            self.fechar_codigo("Erro na conexão com o Banco de Dados...")

    def insercao(self, valores):
        mydb = ConexaoMySQL.conexao(self)
        insert = mydb.cursor()
        query = "INSERT INTO regiao (descricao_regiao, valor_regiao, data_craw) VALUES (%s, %s, %s)"
        data = datetime.now()
        data = data.strftime("%Y/%m/%d %H:%M:%S")

        if not valores:
            self.fechar_codigo("Dados vazios...")

        try:
            for v in valores:
                print(v)
                valor_inserir = (v[0], v[1], data)
                insert.execute(query, valor_inserir)
            mydb.commit()
            print("\nDados inseridos com sucesso!\n")
        except Exception as e:
            print(e)
            self.fechar_codigo("Erro na inserção com o Banco de Dados...")

    def fechar_codigo(self, mensagem):
        print(mensagem)
        from sys import exit
        exit(0)
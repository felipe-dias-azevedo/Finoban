import mysql.connector
from datetime import datetime

class ConexaoMySQL:

    def __init__(self, host, user, password, database):
        self.host = host
        self.user = user
        self.password = password
        self.database = database


    def conexao(self):
        print("Estabelecendo conexão...")
        mydb = mysql.connector.connect(
            host = self.host,
            user = self.user,
            password = self.password,
            database = self.database
        )
        return mydb

    def insercao(self, *valores):
        mydb = ConexaoMySQL.conexao(self)
        insert = mydb.cursor()
        query = "INSERT INTO regiao VALUES (1, %s, %s, %s)"
        for valor in valores:
            for valor_unico in valor:
                print(valor_unico)
                data = datetime.now()
                data = data.strftime("%Y/%m/%d %H:%M:%S")
                valor_inserir = (valor_unico[0], valor_unico[1], data)
                insert.execute(query, valor_inserir)
                mydb.commit()
                print("Dados inseridos")


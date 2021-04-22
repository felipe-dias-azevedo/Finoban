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
        contador = 1
        mydb = ConexaoMySQL.conexao(self)
        insert = mydb.cursor()
        query = "INSERT INTO regiao VALUES (%s, %s, %s, %s)"
        data = datetime.now()
        data_fixa = data
        for valor in valores:
            for valor_unico in valor:
                print(valor_unico)
                data_fixa = data.strftime("%Y/%m/%d %H:%M:%S")
                valor_inserir = (contador,valor_unico[0], valor_unico[1], data_fixa)
                insert.execute(query, valor_inserir)
                mydb.commit()
                contador += 1
                print("Dados inseridos")


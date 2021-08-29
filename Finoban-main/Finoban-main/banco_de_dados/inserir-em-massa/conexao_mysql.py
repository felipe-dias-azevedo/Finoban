import mysql.connector
from tipo_arquivo import TipoArquivo


def fechar_codigo(mensagem):
    print(mensagem)
    from sys import exit
    exit(0)


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
            fechar_codigo("Erro na conexão com o Banco de Dados...")

    def insercao(self, valores, arquivo):
        mydb = ConexaoMySQL.conexao(self)
        insert = mydb.cursor()

        if arquivo == TipoArquivo.CLIENTE:
            query = "INSERT INTO cliente (id_cliente, data_nasc, cep, bairro) " \
                    "VALUES (%s, %s, %s, %s)"
        elif arquivo == TipoArquivo.ACESSO:
            query = "INSERT INTO acesso (" \
                    "id_entrada, data_hora_entrada, data_hora_saida, status_saida, " \
                    "pagina_saida, renda, valor_imovel, tempo_financiamento, " \
                    "porcentagem_renda, banco_escolhido, fk_cliente, fk_regiao) " \
                    "VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)"
        elif arquivo == TipoArquivo.AVALIACAO:
            query = "INSERT INTO avaliacao " \
                    "(id_avaliacao, aval_positivo, data_aval, fk_acesso) " \
                    "VALUES (%s, %s, %s, %s)"

        if not valores:
            fechar_codigo("Dados vazios...")

        try:
            for v in valores:
                print(v)
                insert.execute(query, v)
            mydb.commit()
            print("\nDados inseridos com sucesso!\n")
        except Exception as e:
            print(e)
            fechar_codigo("Erro na inserção com o Banco de Dados...")

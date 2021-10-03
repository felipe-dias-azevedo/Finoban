import csv
from conexao_mysql import ConexaoMySQL
from tipo_arquivo import TipoArquivo

db_host_name = "0.0.0.0"
db_user_name = "root"
db_password = "urubu100"
db_database = "Finoban"

def retira_acentos(texto):
    import unicodedata
    try:
        texto = unicode(texto, 'utf-8')
    except NameError:
        pass
    texto = unicodedata.normalize('NFD', texto) \
        .encode('ascii', 'ignore') \
        .decode("utf-8")
    return str(texto)


def main():
    """
    ler arquivos e inserir no banco de dados.
    """

    cliente_csv = open("dfCliente.csv", newline='', encoding="ISO-8859-1")
    cliente_csv = csv.reader(cliente_csv, delimiter=',', quotechar='|')

    acesso_csv = open("dfAcesso.csv", newline='', encoding="ISO-8859-1")
    acesso_csv = csv.reader(acesso_csv, delimiter=',', quotechar='|')

    avaliacao_csv = open("dfAvaliacao.csv", newline='', encoding="ISO-8859-1")
    avaliacao_csv = csv.reader(avaliacao_csv, delimiter=',', quotechar='|')

    clientes = []
    acessos = []
    avaliacoes = []

    for row in cliente_csv:
        cliente = row
        cliente[-1] = retira_acentos(cliente[-1])
        clientes.append(tuple(cliente))
    for row in acesso_csv:
        acesso = row
        acesso = (acesso[:4], acesso[7:])
        acessos.append(tuple(row))
    for row in avaliacao_csv:
        avaliacoes.append(tuple(row))

    con = ConexaoMySQL(db_host_name, db_user_name, db_password, db_database)
    con.insercao(clientes, TipoArquivo.CLIENTE)
    con.insercao(acessos, TipoArquivo.ACESSO)
    con.insercao(avaliacoes, TipoArquivo.AVALIACAO)


if __name__ == "__main__":
    main()

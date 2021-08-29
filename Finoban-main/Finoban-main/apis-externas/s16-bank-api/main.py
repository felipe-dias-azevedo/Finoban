import sqlite3
from flask import Flask, request, g, jsonify, make_response
from markupsafe import escape

app = Flask(__name__)
app.config['JSON_SORT_KEYS'] = False


@app.errorhandler(500)
def not_found(error):
    # return make_response("Internal Server Error", 500)
    # return 'Error 500 - Internal Server Error'
    return make_response(jsonify({
        "ok": False,
        "status": 500
    }), 500)


@app.errorhandler(405)
def not_found(error):
    # return make_response("Method not Allowed", 405)
    return make_response(jsonify({
        "ok": False,
        "status": 405
    }), 405)


@app.errorhandler(404)
def not_found(error):
    # return make_response("Not Found", 404)
    # return 'Error 404 - Not Found'
    return make_response(jsonify({
        "ok": False,
        "status": 404
    }), 404)


@app.errorhandler(400)
def not_found(error):
    # return make_response("Bad Request", 400)
    # return 'Error 400 - Bad Request'
    return make_response(jsonify({
        "ok": False,
        "status": 400
    }), 400)


DATABASE = 's16bank.db'


def get_db():
    db = getattr(g, '_database', None)
    if db is None:
        db = g._database = sqlite3.connect(DATABASE)
    return db


def create_db():
    from values_db import insert
    insert()


@app.teardown_appcontext
def close_connection(exception):
    db = getattr(g, '_database', None)
    if db is not None:
        db.close()


def select_db(query, args=(), one=False):
    cur = get_db().execute(query, args)
    rv = cur.fetchall()
    cur.close()
    return (rv[0] if rv else None) if one else rv


@app.route('/openbanking/v1/financiamento', methods=['POST'])
def financiamento():
    # create_db()
    body = request.json
    cnpj = body['cnpj']
    renda = body['renda']
    valor_imovel = body['valorImovel']
    tempo_financiamento = body['tempoFinanciamento']

    print("CNPJ:", cnpj)
    print("Renda:", renda)
    print("Valor Imovel:", valor_imovel)
    print("Tempo Financiamento:", tempo_financiamento)

    usuarios = select_db("SELECT Patrimonio, DataNascimento FROM Cliente WHERE CNPJ = ?", (cnpj,))

    if not usuarios:
        resposta = {
            "ok": False,
            "status": 404
        }
        return make_response(jsonify(resposta), 404)

    import time
    from datetime import datetime

    data_agora = datetime.fromtimestamp(time.time())
    data_usuario = datetime.strptime(usuarios[0][1], '%Y-%m-%d')
    if data_agora.month >= data_usuario.month and data_agora.day >= data_usuario.day:
        idade = data_agora.year - data_usuario.year
    else:
        idade = (data_agora.year - data_usuario.year) - 1

    import calculo_taxa

    score_serasa = calculo_taxa.valor_score_serasa(int(cnpj))

    taxas = calculo_taxa.calcular_taxas(
        float(renda),
        float(usuarios[0][0]),
        idade,
        int(tempo_financiamento),
        float(valor_imovel),
        score_serasa
    )

    resposta = {
        "ok": True,
        "status": 200,
        "data": {
            "taxa": taxas["taxa"],
            "taxaAdministracao": taxas["taxa_administracao"],
            "dfi": taxas["dfi"],
            "mip": taxas["mip"],
            "taxaTotal": taxas["taxa_total"]
        }
    }
    return make_response(jsonify(resposta), 200)


@app.route('/openbanking/v1/conta', methods=['POST'])
def conta():
    # create_db()
    body = request.json
    cnpj = body['cnpj']

    print("CNPJ:", cnpj)

    usuarios = select_db("SELECT CNPJ FROM Cliente WHERE CNPJ = ?", (cnpj,))

    if not usuarios:
        status = 404
        possui_conta = False
    else:
        status = 200
        possui_conta = True

    resposta = {
        "ok": True,
        "status": status,
        "data": {
            "banco": "S16 Bank",
            "possuiConta": possui_conta
        }
    }
    return make_response(jsonify(resposta), status)

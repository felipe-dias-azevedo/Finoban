def valor_score_serasa(cnpj: str) -> int:
    import json
    from requests import post, get
    pload = {"cnpj": cnpj}
    try:
        req = post("http://localhost:8082/consultas/v1/score", json=pload)
        req = json.loads(req.content)
        if req["ok"] and req["status"] == 200:
            return req["data"]["score"]
        else:
            raise Exception("Error API Serasa")
    except Exception as e:
        print("Erro na requisição com a API Serasa... ", e)
        from random import randrange
        return randrange(0, 1000)


def calcular_taxas(
        renda_mensal: float,
        patrimonio: float,
        idade_usuario: int,
        tempo_financiamento: int,
        valor_imovel: float,
        score_serasa: int
) -> dict:
    from taxa_financiamento import TaxaFinanciamento

    financ = TaxaFinanciamento(renda_mensal, patrimonio, idade_usuario, tempo_financiamento, valor_imovel, score_serasa)
    taxa = financ.calc_taxa_total()

    taxa_administracao = 1
    seguro_dfi = 0.018
    seguro_mip = financ.calc_seguro_mip()

    y_taxa = (taxa_administracao + seguro_dfi + seguro_mip)

    x_taxa = taxa - y_taxa

    taxas = {
        "taxa": round((x_taxa / 100), 3),
        "taxa_administracao": round((taxa_administracao / 100), 2),
        "dfi": round((seguro_dfi / 100), 6),
        "mip": round((seguro_mip / 100), 6),
        "taxa_total": round((taxa / 100), 3)
    }
    return taxas

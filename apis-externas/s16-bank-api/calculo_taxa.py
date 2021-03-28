def valor_score_serasa(cnpj: str) -> int:
    import json
    from requests import post, get
    pload = {"cnpj": cnpj}
    try:
        req = post("http://localhost:8082/consultas/v1/score", json = pload)
        req = json.loads(req.content)
        if req["ok"] and req["status"] == 200:
            return req["data"]["score"]
        else:
            raise Exception("Error API Serasa")
    except Exception as e:
        print("Erro na requisição com a API Serasa... ", e)
        from random import randrange
        return randrange(0, 1000)


def calcular_taxas (
        renda_mensal: float,
        patrimonio: float,
        idade_usuario: int,
        tempo_financiamento: int,
        valor_imovel: float,
        score_serasa: int,
        taxa_administracao = 0.01,
        seguro_dfi = 0.00015,
        seguro_mip = 0.0002
    ) -> list:
    # renda
    renda = renda_mensal
    p_renda = -750
    # patrimonio
    patri = patrimonio
    p_patri = -100
    # idade
    idade = idade_usuario
    p_idade = 10_000_000
    # tempo de financiamento
    tf = tempo_financiamento
    p_tf = 50_000
    # valor do imovel
    vi = valor_imovel
    p_vi = 350
    # score do serasa
    scs = score_serasa
    p_scs = -5_000

    # taxa de administraçao
    ta = taxa_administracao # 1% de taxa
    p_ta = 1_000
    # taxa de seguro dfi
    dfi = seguro_dfi # 0.015% de seguro dfi
    p_dfi = 200
    # taxa de seguro mip
    mip = seguro_mip # 0.02% de seguro mip
    p_mip = 200

    y_taxa = (
        ((ta * p_ta) + (dfi * p_dfi) + (mip * p_mip))
        /
        ((p_ta + p_dfi + p_mip))
    ) * 6800

    x_taxa = (
        ((renda * p_renda) + (patri * p_patri) + (idade * p_idade) + (tf * p_tf) + (vi * p_vi) + (scs * p_scs))
        /
        (p_renda + p_patri + p_idade + p_tf + p_vi + p_scs)
    )

    taxa = (x_taxa * 0.3) + y_taxa
    taxa_sem_extra = (taxa / 10) - 1.035
    
    taxas = {
        "taxa": round(taxa_sem_extra / 100, 3),
        "taxa_administracao": taxa_administracao,
        "dfi": seguro_dfi,
        "mip": seguro_mip,
        "taxa_total": round(((taxa / 10) / 100), 3)
    }
    return taxas
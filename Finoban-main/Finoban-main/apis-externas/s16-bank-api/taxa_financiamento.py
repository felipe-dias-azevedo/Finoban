class TaxaFinanciamento:
    def __init__(
            self,
            renda_mensal: float,
            patrimonio: float,
            idade_usuario: int,
            tempo_financiamento: int,
            valor_imovel: float,
            score_serasa: int
    ):
        self.renda = renda_mensal
        self.patrimonio = patrimonio
        self.idade = idade_usuario
        self.tempo_financ = tempo_financiamento
        self.val_imovel = valor_imovel
        self.score_serasa = score_serasa

    def financiar(self) -> dict:
        imovel = self.val_imovel
        taxa_juros = (self.calc_taxa_idade() + self.calc_taxa_score()) / 2 / 100
        # taxa_juros= 0.06
        taxa_juros_mensal = taxa_juros

        primeira_prestacao = 0
        valor_a_pagar = 0
        anos_pagos = 0
        parcelas = []

        while anos_pagos < self.tempo_financ:  # Max = 35 anos
            anos_pagos += 1  # define ano de pagamento
            prestacao_sem_taxa = imovel / self.tempo_financ  # define valor da prestacao sem juros
            prestacao_com_taxa = imovel * taxa_juros_mensal  # define valor de juros da prestacao
            if anos_pagos == 1:
                primeira_prestacao = (prestacao_com_taxa / 12) + (prestacao_sem_taxa / 12)
            imovel = imovel - prestacao_sem_taxa  # subtrai valor pago do total do imovel
            valor_a_pagar += (prestacao_sem_taxa + prestacao_com_taxa)  # valor total pago
            parcelas.append(prestacao_sem_taxa + prestacao_com_taxa)

        financiamento = {
            "primeira_prestacao": primeira_prestacao,
            "valor_total": valor_a_pagar,
            "parcelas": parcelas
        }
        return financiamento

    def calc_taxa_renda(self):
        renda = self.renda
        # ideal = self.financiar()['primeira_prestacao']
        # print("1a parcela:", ideal)
        parcelas = self.financiar()['parcelas']
        # ideal = sum(parcelas) / (len(parcelas) * 12)
        # print("parcela media:", ideal)
        # ideal = parcelas[len(parcelas) // 2] / 12
        # print("parcela mediana:", ideal)
        ideal = parcelas[len(parcelas) // 4] / 12  # parcela no 1º quartil
        # print("parcela 1o quartil:", ideal)

        x = renda / ideal

        if x < 0.3:
            x = 0.3
        if x > 1.8:
            x = 1.8

        y = -1.8 * x ** 2 + 11
        return round(y, 2)

    def calc_taxa_idade(self):
        idade_max = 65  # minimo aposentadoria homem
        ideal = self.idade + self.tempo_financ

        x = ideal / idade_max

        if x < 0.4:
            x = 0.4
        if x > 1.45:
            x = 1.45

        y = 3 * x ** 2 + 4.7
        return round(y, 2)

    def calc_taxa_patrimonio(self):
        patrimonio = self.patrimonio + (self.renda * 12 * self.tempo_financ)
        ideal = self.financiar()['valor_total']
        # print(patrimonio, ideal)
        x = patrimonio / ideal

        if x < 0.3:
            x = 0.3
        if x > 1.55:
            x = 1.55

        y = -2 * x ** 2 + 10
        return round(y, 2)

    def calc_taxa_score(self):
        if self.idade < 60:
            ideal = 500
        else:
            ideal = 800

        x = self.score_serasa / ideal

        if x < 0.6:
            x = 0.6
        if x > 1.4:
            x = 1.4

        y = -2 * x ** 2 + 10
        return round(y, 2)

    def calc_taxa_total(self):
        renda = self.calc_taxa_renda()
        idade = self.calc_taxa_idade()
        patrimonio = self.calc_taxa_patrimonio()
        score = self.calc_taxa_score()
        y = (renda * 0.30 + idade * 0.35 + patrimonio * 0.15 + score * 0.20)
        return round(y, 2)

    def calc_seguro_mip(self):
        if self.idade < 31:
            y = 0.02
        elif self.idade < 36:
            y = 0.028
        elif self.idade < 41:
            y = 0.042
        elif self.idade < 46:
            y = 0.045
        elif self.idade < 51:
            y = 0.077
        elif self.idade < 56:
            y = 0.12
        elif self.idade < 61:
            y = 0.2
        elif self.idade < 66:
            y = 0.35
        elif self.idade < 71:
            y = 0.49
        else:
            y = 0.69
        return round(y, 3)


if __name__ == "__main__":
    f = TaxaFinanciamento(3_000, 50_000, 30, 30, 500_000, 800)
    print("taxa renda:", f.calc_taxa_renda(), "%")
    print("taxa idade:", f.calc_taxa_idade(), "%")
    print("taxa patrimonio:", f.calc_taxa_patrimonio(), "%")
    print("taxa score:", f.calc_taxa_score(), "%")
    print("\ntaxa total:", f.calc_taxa_total(), "%")
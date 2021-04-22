class Imoveis:
    user_agent = 'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36'

    def zapimoveis(self, regiao="se", zona="centro"):
        from urllib.request import Request, urlopen
        from bs4 import BeautifulSoup
        from scipy import stats

        url = f"https://www.zapimoveis.com.br/venda/imoveis/sp+sao-paulo+{zona}+{regiao}/?transacao=Venda"

        request = Request(url)
        request.add_header('User-Agent', self.user_agent)
        response = urlopen(request)

        content = ""
        if response.status == 200:
            print('Requisição zapimoveis.com.br -> OK... bairro =', regiao.upper(), ' | regiao =', zona.upper())
            content = response.read()
            soup = BeautifulSoup(content, 'html.parser')

            precos = []
            for price in soup.find_all('p', class_="simple-card__price js-price heading-regular heading-regular__bolder align-left"):
                dot_count = 0
                price_str = price.get_text()[8:-8]
                for i in price_str:
                    if i == '.':
                        dot_count += 1
                if dot_count == 1:
                    precos.append(round(float(price_str) * 1000))
                elif dot_count == 2:
                    new_price_str = ''
                    for j in price_str:
                        if j != '.':
                            new_price_str += j
                    precos.append(round(float(new_price_str)))
            return stats.trim_mean(precos, 0.2)
        else:
            raise Exception("Error -> Request failed")

    def quintoandar(self, regiao="centro"):
        from urllib.request import Request, urlopen
        from bs4 import BeautifulSoup
        from scipy import stats
        import re

        url = f'https://www.quintoandar.com.br/comprar/imovel/{regiao}-sao-paulo-sp-brasil/2-quartos/de-150000-a-3000000-venda '
        request = Request(url)
        request.add_header('User-Agent', self.user_agent)
        response = urlopen(request)

        content = ""
        if response.status == 200:
            print('Requisição quintoandar.com.br -> OK... regiao =', regiao.upper())
            content = response.read()
            soup = BeautifulSoup(content, 'html.parser')

            precos = []
            for div in soup.find_all('div', class_="m82tat-1 tOXvs"):
                preco = div.find('p', class_="sc-bdVaJa gVbDUW").get_text()
                new_preco = float(re.sub(r"\W", "", preco[3:], flags=re.I))
                if new_preco != 0:
                    precos.append(new_preco)
            return stats.trim_mean(precos, 0.2)
        else:
            raise Exception("Error -> Request failed")

    def run(self):
        dados_imoveis = []

        val_centro = []
        val_consolacao = []
        val_brooklin = []
        val_mooca = []
        val_santo_amaro = []
        val_interlagos = []

        try:
            val_centro.append(round(self.quintoandar("centro")))
            val_consolacao.append(round(self.quintoandar("consolacao")))
            val_brooklin.append(round(self.quintoandar("brooklin")))
            val_mooca.append(round(self.quintoandar("mooca")))
            val_santo_amaro.append(round(self.quintoandar("santo-amaro")))
            val_interlagos.append(round(self.quintoandar("interlagos")))
        except Exception as e:
            print("Error QuintoAndar")
            print(e)

        try:
            val_centro.append(round(self.zapimoveis()))
            val_consolacao.append(round(self.zapimoveis("consolacao")))
            val_brooklin.append(round(self.zapimoveis("brooklin", "zona-sul")))
            val_mooca.append(round(self.zapimoveis("mooca", "zona-leste")))
            val_santo_amaro.append(round(self.zapimoveis("sto-amaro", "zona-sul")))
            val_interlagos.append(round(self.zapimoveis("interlagos", "zona-sul")))
        except Exception as e:
            print("Error ZapImoveis")
            print(e)

        dados_imoveis.append(tuple(["Centro", int(sum(val_centro) / len(val_centro))]))
        dados_imoveis.append(tuple(["Consolação", int(sum(val_consolacao) / len(val_consolacao))]))
        dados_imoveis.append(tuple(["Brooklin", int(sum(val_brooklin) / len(val_brooklin))]))
        dados_imoveis.append(tuple(["Mooca", int(sum(val_mooca) / len(val_mooca))]))
        dados_imoveis.append(tuple(["Santo Amaro", int(sum(val_santo_amaro) / len(val_santo_amaro))]))
        dados_imoveis.append(tuple(["Interlagos", int(sum(val_interlagos) / len(val_interlagos))]))

        return dados_imoveis


if __name__ == "__main__":
    i = Imoveis()
    print(i.run())

class Imoveis:
    def __init__(self):
        self.html = ""

    def get_zapimoveis(self, regiao="se", zona="centro"):
        from urllib.request import Request, urlopen
        from bs4 import BeautifulSoup

        url = f"https://www.zapimoveis.com.br/venda/imoveis/sp+sao-paulo+{zona}+{regiao}/?transacao=Venda"
        USER_AGENT = 'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36'
        request = Request(url)
        request.add_header('User-Agent', USER_AGENT)
        response = urlopen(request)
        
        content = ""
        if response.status == 200:
            print('Requisição zapimoveis.com.br -> OK... bairro =', regiao.upper(), ' | regiao =', zona.upper())
            content = response.read()
            soup = BeautifulSoup(content, 'html.parser')

            precos = []
            for price in soup.find_all('p', class_="simple-card__price js-price heading-regular heading-regular__bolder align-left"):
                dot_count = 0
                price_str = price.get_text()[8:-11]
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
            # print(precos)
            from scipy import stats
            return stats.trim_mean(precos, 0.2) 
        else:
            raise Exception("Error -> Request failed")

    def get_quintoandar(self, regiao="centro"):
        import re
        from urllib.request import Request, urlopen
        from bs4 import BeautifulSoup

        url = f"https://www.quintoandar.com.br/comprar/imovel/{regiao}-sao-paulo-sp-brasil/2-quartos/de-150000-a-3000000-venda"
        USER_AGENT = 'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36'
        request = Request(url)
        request.add_header('User-Agent', USER_AGENT)
        response = urlopen(request)

        content = ""
        if response.status == 200:
            print('Requisição quintoandar.com.br -> OK... regiao =', regiao.upper())
            content = response.read()
            soup = BeautifulSoup(content, 'html.parser')
        
            tipos = []
            descs = []
            condos = []
            precos = []
            for div in soup.find_all('div', class_="hhh4j4-0 QJYUe"):
                tipo = div.find('span', class_="hhh4j4-2 hyCyhO sc-bdVaJa hTfYit").get_text()
                descr = div.find('span', class_="hhh4j4-1 ldRYl sc-bdVaJa dScILH").get_text()
                tipos.append(tipo)
                descs.append(descr)
            for div in soup.find_all('div', class_="m82tat-1 tOXvs"):
                condo = div.find('p', class_="m82tat-3 kVYbKp sc-bdVaJa iTBXOV").get_text()
                preco = div.find('p', class_="sc-bdVaJa gVbDUW").get_text()
                condos.append(condo)
                new_preco = float(re.sub(r"\W", "", preco[3:], flags=re.I))
                if new_preco != 0:
                    precos.append(new_preco)
            # for i in range(10):
                # print(tipos[i], "=>", descs[i], "-", precos[i], "/", condos[i])
            return sum(precos) / len(precos)
        else:
            raise Exception("Error -> Request failed")

    def run(self):
        import matplotlib
        import matplotlib.pyplot as plt
        import numpy as np
        try:
            raise Exception('teste zap')
            centro = round(self.get_quintoandar("centro"))
            consolacao = round(self.get_quintoandar("consolacao"))
            brooklin = round(self.get_quintoandar("brooklin"))
            mooca = round(self.get_quintoandar("mooca"))
            santo_amaro = round(self.get_quintoandar("santo-amaro"))
            interlagos = round(self.get_quintoandar("interlagos"))

            print("centro=",centro, "consolacao=", consolacao,
            "brooklin=", brooklin, "santo_amaro=", santo_amaro,
            "interlagos=", interlagos, "mooca=", mooca)

            labels = ['centro', 'consolacao', 'brooklin', 'santo_amaro', 'interlagos', 'mooca']
            means = [centro, consolacao, brooklin, santo_amaro, interlagos, mooca]
            x = np.arange(len(labels))
            fig, ax = plt.subplots()
            rects = ax.bar(x - 0.35/2, means, 0.35, label='Means')
            ax.set_ylabel('Valor do Imóvel')
            ax.set_title('Médias de Preço de Imovéis nas Regiões de SP')
            ax.set_xticks(x)
            ax.set_xticklabels(labels)
            ax.legend()
            for rect in rects:
                height = rect.get_height()
                ax.annotate('{}'.format(height),
                            xy=(rect.get_x() + rect.get_width() / 2, height),
                            xytext=(0, 3),  # 3 points vertical offset
                            textcoords="offset points",
                            ha='center', va='bottom')
            plt.show()
        except Exception as e:
            print(e)
            centro = round(self.get_zapimoveis())
            consolacao = round(self.get_zapimoveis("consolacao"))
            brooklin = round(self.get_zapimoveis("brooklin", "zona-sul"))
            mooca = round(self.get_zapimoveis("mooca", "zona-leste"))
            santo_amaro = round(self.get_zapimoveis("sto-amaro", "zona-sul"))
            interlagos = round(self.get_zapimoveis("interlagos", "zona-sul"))

            print("centro=",centro, "consolacao=", consolacao,
            "brooklin=", brooklin, "santo_amaro=", santo_amaro,
            "interlagos=", interlagos, "mooca=", mooca)

            labels = ['centro', 'consolacao', 'brooklin', 'santo_amaro', 'interlagos', 'mooca']
            means = [centro, consolacao, brooklin, santo_amaro, interlagos, mooca]
            x = np.arange(len(labels))
            fig, ax = plt.subplots()
            rects = ax.bar(x - 0.35/2, means, 0.35, label='Means')
            ax.set_ylabel('Valor do Imóvel')
            ax.set_title('Médias de Preço de Imovéis nas Regiões de SP')
            ax.set_xticks(x)
            ax.set_xticklabels(labels)
            ax.legend()
            for rect in rects:
                height = rect.get_height()
                ax.annotate('{}'.format(height),
                            xy=(rect.get_x() + rect.get_width() / 2, height),
                            xytext=(0, 3),  # 3 points vertical offset
                            textcoords="offset points",
                            ha='center', va='bottom')
            plt.show()


if __name__ == "__main__":
    i = Imoveis()
    # i.get_zapimoveis()
    # i.get_quintoandar()
    i.run()
    # i.test_quintoandar()
    # i.get_zapimoveis()
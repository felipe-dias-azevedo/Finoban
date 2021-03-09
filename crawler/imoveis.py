class Imoveis:
    def __init__(self):
        self.html = ""

    def get_zapimoveis(self):
        from urllib.request import Request, urlopen
        from bs4 import BeautifulSoup

        url = "https://www.zapimoveis.com.br/"
        USER_AGENT = 'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36'
        request = Request(url)
        request.add_header('User-Agent', USER_AGENT)
        response = urlopen(request)
        print(response.status)

    def get_quintoandar(self, regiao="centro"):
        import re
        # import requests
        from urllib.request import Request, urlopen
        from bs4 import BeautifulSoup

        url = f"https://www.quintoandar.com.br/comprar/imovel/{regiao}-sao-paulo-sp-brasil/2-quartos/de-150000-a-3000000-venda"
        USER_AGENT = 'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36'
        request = Request(url)
        request.add_header('User-Agent', USER_AGENT)
        response = urlopen(request)

        content = ""
        if response.status == 200:
            print('Requisição quintoandar.com.br -> OK... regiao =', regiao)
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

    def run_quintoandar(self):
        import matplotlib
        import matplotlib.pyplot as plt
        import numpy as np
        try:
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
            self.get_zapimoveis()


if __name__ == "__main__":
    i = Imoveis()
    # i.get_zapimoveis()
    # i.get_quintoandar()
    i.run_quintoandar()
    # i.test_quintoandar()
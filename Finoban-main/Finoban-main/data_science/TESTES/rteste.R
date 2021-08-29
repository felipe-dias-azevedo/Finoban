n<-1000
n

set.seed(123)

idade <-round(rnorm(n,40,5))
regiao <- round(runif(n,1,6))
regiao.r <-factor(regiao,
                  levels = c(1,2,3,4,5,6),
                  labels = c("norte","leste","sudeste",
                             "sudoeste","oeste","nordeste")
                  )

valorImovel <- round(rnorm(n,100000,25000),2)
renda <- round(rnorm(n,6000,1000),2)
banco <- round(runif(n,1,3))
banco.r <- factor(banco,
                  levels= c(1,2,3),
                  labels = c("Banco do Presil","S16 Bank","Banco Cifra")
                  ) 
avaliacao <-round(runif(n,0,1))
avaliacao.r <- factor(avaliacao,
                    levels = c(0,1),
                    labels = c("ruim","boa")
                    )

head(table(valorImovel))


df <- data.frame(idade,regiao.r,valorImovel,renda,banco.r,avaliacao.r)

barplot(table(avaliacao.r),
        col=c("#972838", "#278284"),
        main = "Avaliação")

barplot(table(banco.r),
        main= "Bancos escolhidos")

barplot(table(regiao.r),
        main= "Região")

plot(table(idade,valorImovel))


head(valorImovel)










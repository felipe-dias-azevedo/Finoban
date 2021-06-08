set.seed(123)

id_cliente <- c(1:n)

data_nasc <-sample(seq(as.Date('1970/01/01'), as.Date('2000/01/01'), by="day"), n)
data_nasc

dfCliente <- data.frame(id_cliente,data_nasc)

cliente

id_cliente %>%
 full_join(local, by ="id")

library(tidyr)

write.table(dfCliente,"C:\\Users\\catarina\\Desktop\\Finoban\\data_science\\cliente2.csv", row.names = FALSE)

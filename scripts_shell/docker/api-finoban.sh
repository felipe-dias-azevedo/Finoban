#!/bin/bash

# Variaveis
DIR='../../scripts_shell/docker/api-finoban'
APP='app-finoban'
SEP='------------------------------------------'

# Comunicando inicio do script
echo $SEP
echo "Iniciando o script..."
echo $SEP

# Limpa as sujeiras do diretório
echo $SEP
echo "Criando JAR"
echo $SEP
cd ../../api-finoban/finoban
mvn clean

# Cria o jar
./mvnw install

# Move .jar para pasta do Dockerfile
mv target/demo-0.0.1-SNAPSHOT.jar $DIR

# Renomeia o arquivo .jar
cd $DIR
mv demo-0.0.1-SNAPSHOT.jar api-finoban.jar

# Apaga imagem docker
echo $SEP
echo "Criando imagem docker"
echo $SEP
sudo docker rmi -f $APP:1.0

# Cria a imagem docker
sudo docker build -t $APP:1.0 .

# Verifica se conrainer já existe e caso afirmativo apaga
echo $SEP
echo "Criando container"
echo $SEP
sudo docker stop $APP
sudo docker rm $APP

# Cria o container e inicia ele
sudo docker run --name $APP -it -p 8080:8080 -d $APP:1.0

# Apaga o arquivo .jar
rm api-finoban.jar
cd ..

# Comunicando final do script
echo $SEP
echo "Iniciando o script..."
echo $SEP
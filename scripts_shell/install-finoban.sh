#!/bin/bash

# definindo senha para usuario ubuntu
echo "[Finoban]: Definindo senha para usuario ubuntu"
echo "Defina a senha como '123'"
sudo passwd ubuntu

# atualizando pacotes
echo "[Finoban]: Atulizando pacotes do sistema"
sudo apt update
sudo apt upgrade

# instalando dependencias
echo "[Finoban]: Instalando dependências"
sudo apt install docker.io python3-pip python3-dev python3-venv golang nodejs npm r-base maven

# habilitando o docker
echo "[Finoban]: Habilitando o docker no sistema"
sudo systemctl start docker
sudo systemctl enable docker

# instalando o mysql no docker
echo "[Finoban]: Instalando o mysql no docker"
sudo docker pull mysql
sudo docker run -d -p 3306:3306 -e"MYSQL_ROOT_PASSWORD=urubu100" mysql

# instalando dependencias do pip python
echo "[Finoban]: Instalando dependencias do pip do python"
pip3 install mysql-connector-python flask requests bs4 scipy

# instalando o java
echo "[Finoban]: Instalando o Java JDK 11 no sistema"
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"
sdk install java 11.0.9.j9-adpt

# instalando o dotnet
echo "[Finoban]: Instalando o .NET Core 3.1 no sistema"
wget https://packages.microsoft.com/config/ubuntu/20.04/packages-microsoft-prod.deb -O packages-microsoft-prod.deb
sudo dpkg -i packages-microsoft-prod.deb
rm packages-microsoft-prod.deb
sudo apt-get update; \
  sudo apt-get install -y apt-transport-https && \
  sudo apt-get update && \
  sudo apt-get install -y dotnet-sdk-3.1

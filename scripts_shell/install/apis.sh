#!/bin/bash

echo "[Finoban]: Instalando APIs Externas na máquina"

echo "[Finoban]: Instalando dependências"
sudo apt update && sudo apt upgrade -y
curl -sL https://deb.nodesource.com/setup_current.x | sudo -E bash -
sudo apt install -y python3-pip python3-dev python3-venv golang nodejs npm

echo "[Finoban]: Instalando dependências do pip do python"
pip3 install mysql-connector-python flask requests bs4 scipy

echo "[Finoban]: Instalando o .NET Core 3.1 no sistema"
wget https://packages.microsoft.com/config/ubuntu/20.04/packages-microsoft-prod.deb -O packages-microsoft-prod.deb
sudo dpkg -i packages-microsoft-prod.deb
rm packages-microsoft-prod.deb
sudo apt-get update; \
  sudo apt-get install -y apt-transport-https && \
  sudo apt-get update && \
  sudo apt-get install -y dotnet-sdk-3.1

echo "[Finoban]: Copiando o Script de iniciação"
cp -rf ~/Finoban/scripts_shell/start/apis/ ~/
cp ~/Finoban/scripts_shell/start/apis.sh ~/

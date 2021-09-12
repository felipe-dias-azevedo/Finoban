#!/bin/bash

echo "[Finoban]: Instalando Frontend na máquina"

echo "[Finoban]: Instalando dependências"
sudo apt update && sudo apt upgrade -y

echo "[Finoban]: Instalando Node [latest] no Sistema"
curl -sL https://deb.nodesource.com/setup_current.x | sudo -E bash -
sudo apt install -y nodejs npm

echo "[Finoban]: Instalando pacotes do Node no Frontend"
cd ~/Finoban/frontend/site_react/
npm i
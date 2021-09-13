#!/bin/bash

echo "[Finoban]: Instalando Backend na Máquina"

echo "[Finoban]: Instalando dependências"
sudo apt update && sudo apt upgrade -y

echo "[Finoban]: Instalando o Java JDK 11 no sistema"
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"
sdk install java 11.0.9.j9-adpt

echo "[Finoban]: Instalando o Maven no sistema"
sudo apt install -y maven

echo "[Finoban]: Copiando o Script de iniciação"
cp ~/Finoban/scripts_shell/start/backend.sh ~/
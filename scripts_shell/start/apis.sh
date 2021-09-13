#!/bin/bash

echo "[Finoban]: Iniciando as APIs Externas"

source apis/iniciar_serasa.sh &>/dev/null &

echo 'API Serasa iniciada'

source apis/iniciar_s16.sh &>/dev/null &

echo 'API S16 iniciada'

source apis/iniciar_presil.sh &>/dev/null &

echo 'API Presil iniciada'

source apis/iniciar_cifra.sh

echo 'API Cifra iniciada'

echo '\n'

fg
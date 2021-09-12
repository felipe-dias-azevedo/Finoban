#!/bin/bash

source iniciar_serasa.sh &>/dev/null &

echo 'api serasa iniciada'

source iniciar_s16.sh &>/dev/null &

echo 'api s16 iniciada'

#source iniciar_cifra.sh &>/dev/null &

#echo 'api cifra iniciada'

source iniciar_presil.sh &>/dev/null &

echo 'api presil iniciada'

source iniciar_cifra.sh

echo 'api cifra iniciada'
#!/usr/bin/env bash

# Validar argumento que contém o endereço do health check
[[ -z $1 ]] && { echo 'Argumento endereço não informado'; exit 1; } || endereco=$1 

# Obter o id da instância
instance_id=$(curl -s http://169.254.169.254/latest/meta-data/instance-id)

# Obter o status code do serviço
resposta=$(curl -s -o /dev/null -w "%{http_code}" $endereco)

# Validar a resposta, se true, deve enviar 1 se false deve enviar 0
[[ $resposta -eq 200 ]] && metrica=1 || metrica=0

/usr/bin/env aws cloudwatch put-metric-data --metric-name APIHealthCheck --namespace Custom --unit Count --value $metrica --dimensions InstanceId=$instance_id
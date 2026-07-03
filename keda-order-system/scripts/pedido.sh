#!/bin/bash

URL="http://localhost:8081/orders"

for i in $(seq 1 200)
do
  curl --request POST \
    --url $URL \
    --header 'Content-Type: application/json' \
    --data "{ \"id\":$i, \"customer\":\"Adriano\", \"value\":250.50 }"

  echo " -> Pedido $i enviado"
done
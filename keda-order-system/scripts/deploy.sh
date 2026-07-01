#!/bin/bash

set -e

kubectl apply -f k8s/namespace.yaml

kubectl apply -f k8s/kafka

kubectl apply -f k8s/producer

kubectl apply -f k8s/consumer

kubectl apply -f k8s/keda

kubectl apply -f k8s/monitoring
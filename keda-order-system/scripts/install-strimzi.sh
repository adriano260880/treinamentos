#!/bin/bash

set -e

echo ""
echo "====================================="
echo "Installing Strimzi"
echo "====================================="

helm repo add strimzi https://strimzi.io/charts/ >/dev/null 2>&1 || true
helm repo update

kubectl create namespace kafka --dry-run=client -o yaml | kubectl apply -f -

helm upgrade --install strimzi \
    strimzi/strimzi-kafka-operator \
    --namespace kafka

echo ""
echo "Waiting Strimzi..."

kubectl rollout status deployment/strimzi-cluster-operator \
    -n kafka

echo ""
echo "Deploying Kafka Cluster..."

kubectl apply -f k8s/kafka/

echo ""
echo "Waiting Kafka..."

kubectl wait \
    kafka/kafka \
    --for=condition=Ready \
    --timeout=300s \
    -n kafka

kubectl wait kafkatopic/orders \
    -n kafka \
    --for=condition=Ready \
    --timeout=120s || true

kubectl get pods -n kafka
kubectl get svc -n kafka
kubectl get kafka -n kafka

echo ""
echo "Strimzi Installed."
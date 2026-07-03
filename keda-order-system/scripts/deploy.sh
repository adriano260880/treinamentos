#!/bin/bash
set -e

apply() {
  echo "Applying $1"
  kubectl apply -f "$1"
}

apply k8s/namespace.yaml

apply k8s/kafka/02-kafka.yaml

apply k8s/producer/deployment.yaml
apply k8s/producer/service.yaml

apply k8s/consumer/deployment.yaml
apply k8s/consumer/scaledobject.yaml

apply k8s/monitoring/namespace.yaml
apply k8s/monitoring/prometheus.yaml
apply k8s/monitoring/prometheus-config.yaml
apply k8s/monitoring/prometheus-service.yaml

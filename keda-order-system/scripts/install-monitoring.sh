#!/bin/bash
set -e

echo
echo "====================================="
echo "Installing Monitoring"
echo "====================================="

kubectl apply -f k8s/monitoring/namespace.yaml

kubectl apply -f k8s/monitoring/prometheus-config.yaml
kubectl apply -f k8s/monitoring/prometheus-service.yaml
kubectl apply -f k8s/monitoring/prometheus.yaml

kubectl rollout status deployment/prometheus -n monitoring

kubectl apply -f k8s/monitoring/datasources/

kubectl apply -f k8s/monitoring/grafana-dashboard-provider.yaml
kubectl apply -f k8s/monitoring/grafana-dashboard-configmap.yaml

kubectl apply -f k8s/monitoring/grafana-service.yaml
kubectl apply -f k8s/monitoring/grafana.yaml

kubectl rollout status deployment/grafana -n monitoring

echo
echo "Monitoring Installed."
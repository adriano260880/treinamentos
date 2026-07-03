#!/bin/bash

set -e

echo ""
echo "====================================="
echo "Installing KEDA"
echo "====================================="

helm repo add kedacore https://kedacore.github.io/charts >/dev/null 2>&1 || true

helm repo update

helm upgrade --install keda \
    kedacore/keda \
    --namespace keda \
    --create-namespace

kubectl rollout status deployment/keda-operator \
    -n keda

echo ""
echo "KEDA Installed."
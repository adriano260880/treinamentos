#!/bin/bash

set -e

echo ""
echo "======================================"
echo "Loading Producer"
echo "======================================"

kind load docker-image producer:1.0.0 \
    --name kafka-lab

echo ""
echo "======================================"
echo "Loading Consumer"
echo "======================================"

kind load docker-image consumer:1.0.0 \
    --name kafka-lab
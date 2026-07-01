#!/bin/bash

set -e

echo "======================================"
echo "Building Producer Image"
echo "======================================"

docker build \
    -f producer/Dockerfile \
    -t producer:1.0 \
    .

echo

echo "======================================"
echo "Building Consumer Image"
echo "======================================"

docker build \
    -f consumer/Dockerfile \
    -t consumer:1.0 \
    .
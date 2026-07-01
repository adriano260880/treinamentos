#!/bin/bash

kind create cluster \
    --name kafka-lab \
    --config kind/cluster.yaml
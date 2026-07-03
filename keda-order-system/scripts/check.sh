#!/usr/bin/env bash

set -euo pipefail

GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m'

echo ""
echo "==========================================="
echo " KEDA Order System - Environment Check"
echo "==========================================="
echo ""

check_command() {

    local cmd="$1"
    local description="$2"

    printf "%-20s" "$description"

    if command -v "$cmd" >/dev/null 2>&1; then
        echo -e "${GREEN}OK${NC}"
    else
        echo -e "${RED}NOT FOUND${NC}"
        MISSING=true
    fi
}

MISSING=false

check_command java       "Java"
check_command mvn        "Maven"
check_command docker     "Docker"
check_command kubectl    "Kubectl"
check_command kind       "Kind"
check_command helm       "Helm"
check_command curl       "Curl"

echo ""

############################################################
# Java
############################################################

if command -v java >/dev/null 2>&1; then
    echo -e "${BLUE}Java Version${NC}"
    java -version
    echo ""
fi

############################################################
# Maven
############################################################

if command -v mvn >/dev/null 2>&1; then
    echo -e "${BLUE}Maven Version${NC}"
    mvn -version
    echo ""
fi

############################################################
# Docker
############################################################

if command -v docker >/dev/null 2>&1; then

    echo -e "${BLUE}Docker Version${NC}"
    docker --version
    echo ""

    if docker info >/dev/null 2>&1; then
        echo -e "${GREEN}Docker daemon is running.${NC}"
    else
        echo -e "${RED}Docker daemon is NOT running.${NC}"
        exit 1
    fi

    echo ""
fi

############################################################
# Kubectl
############################################################

if command -v kubectl >/dev/null 2>&1; then

    echo -e "${BLUE}Kubectl Version${NC}"
    kubectl version --client
    echo ""
fi

############################################################
# Kind
############################################################

if command -v kind >/dev/null 2>&1; then

    echo -e "${BLUE}Kind Version${NC}"
    kind --version
    echo ""
fi

############################################################
# Helm
############################################################

if command -v helm >/dev/null 2>&1; then

    echo -e "${BLUE}Helm Version${NC}"
    helm version
    echo ""
fi

############################################################
# Cluster
############################################################

echo -e "${BLUE}Kind Cluster${NC}"

if kind get clusters | grep -q kafka-lab; then

    echo -e "${GREEN}Cluster kafka-lab found.${NC}"

    kubectl config use-context kind-kafka-lab >/dev/null

else

    echo -e "${YELLOW}Cluster kafka-lab not found.${NC}"

fi

echo ""

############################################################

if [ "$MISSING" = true ]; then

    echo -e "${RED}Some required tools are missing.${NC}"
    exit 1

fi

echo -e "${GREEN}Environment OK.${NC}"
PROJECT_NAME=keda-order-system

.PHONY: help

help:
	@echo ""
	@echo "===== KEDA Order System ====="
	@echo ""
	@echo "make build           -> Maven Build"
	@echo "make docker-build    -> Build Docker Images"
	@echo "make docker-load     -> Load Images into Kind"
	@echo "make deploy          -> Deploy Kubernetes"
	@echo "make up              -> Build + Load + Deploy"
	@echo "make logs            -> Consumer Logs"
	@echo "make status          -> Pods"
	@echo "make clean"
	@echo ""

build:
	mvn clean install

docker-build:
	./scripts/docker-build.sh

docker-load:
	./scripts/docker-load.sh

deploy:
	./scripts/deploy.sh

up: build docker-build docker-load deploy

status:
	kubectl get pods -A

logs:
	kubectl logs -n order-system deployment/consumer -f

clean:
	mvn clean
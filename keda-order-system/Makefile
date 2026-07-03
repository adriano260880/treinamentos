PROJECT_NAME := keda-order-system

.PHONY: help build rebuild docker-build docker-load deploy \
        create-cluster destroy-cluster up down restart \
        clean status logs producer consumer kafka prometheus grafana \
        kafka-ui send

help:
	@echo ""
	@echo "========== $(PROJECT_NAME) =========="
	@echo ""
	@echo "Infrastructure"
	@echo "  make create-cluster"
	@echo "  make infra"
	@echo "  make destroy-cluster"
	@echo ""
	@echo "Build"
	@echo "  make build"
	@echo "  make rebuild"
	@echo "  make docker-build"
	@echo "  make docker-load"
	@echo ""
	@echo "Deploy"
	@echo "  make deploy"
	@echo "  make up"
	@echo "  make down"
	@echo "  make restart"
	@echo ""
	@echo "Utilities"
	@echo "  make status"
	@echo "  make logs"
	@echo "  make producer"
	@echo "  make consumer"
	@echo "  make kafka"
	@echo "  make prometheus"
	@echo "  make grafana"
	@echo "  make kafka-ui"
	@echo "  make send"
	@echo "  make check"
	@echo "  make porta-producer"
	@echo ""

create-cluster:
	./scripts/create-cluster.sh

destroy-cluster:
	./scripts/destroy-cluster.sh

infra:
	./scripts/install-strimzi.sh
	./scripts/install-keda.sh
	./scripts/install-monitoring.sh

build:
	mvn clean install

rebuild: clean build

docker-build:
	./scripts/docker-build.sh

docker-load:
	./scripts/docker-load.sh

deploy:
	./scripts/deploy.sh

up: build docker-build docker-load deploy

down:
	kubectl delete -f k8s --ignore-not-found=true

restart: down deploy

clean:
	mvn clean

status:
	kubectl get pods -A

logs:
	kubectl logs -n order-system deployment/consumer -f

producer:
	kubectl logs -n order-system deployment/producer -f

consumer:
	kubectl logs -n order-system deployment/consumer -f

kafka:
	kubectl logs -n kafka deployment/strimzi-cluster-operator -f

porta-producer:
	kubectl port-forward deployment/producer 8081:8081 -n order-system

prometheus:
	kubectl port-forward -n monitoring svc/prometheus 9090:9090

grafana:
	kubectl port-forward -n monitoring svc/grafana 3000:3000

kafka-ui:
	kubectl port-forward -n kafka svc/kafka-ui 8080:8080

send:
	./scripts/pedido.sh

check:
	./scripts/check.sh
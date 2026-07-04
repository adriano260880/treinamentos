# Load Tests

Todos os testes de carga do projeto são executados utilizando **k6**.

## Estrutura

```
k6/
    review-post.js
```

---

# Executando

## 50 usuários

```
k6 run review-post.js
```

---

## 500 usuários

```
k6 run review-post-500.js
```

---

## 1000 usuários

```
k6 run review-post-1000.js
```

---

# Objetivos

Durante os testes serão monitoradas as seguintes métricas:

* Requests por segundo (RPS)
* Latência média
* P90
* P95
* P99
* Taxa de erro
* Uso de CPU
* Uso de memória
* Tempo de resposta do MongoDB

---

# Cenários

## Baseline

50 usuários por 30 segundos.

---

## Carga

500 usuários por 30 segundos.

---

## Alta carga

1000 usuários por 30 segundos.

---

## Ramp-Up

Aumento gradual de carga.

---

## Spike Test

Aumento abrupto de usuários seguido de redução imediata.

---

## Stress Test

Carga crescente até encontrar o limite da aplicação.

---

Todos os resultados devem ser registrados em `docs/performance.md`.

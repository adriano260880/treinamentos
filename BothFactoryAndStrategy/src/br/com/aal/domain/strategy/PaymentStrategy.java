package br.com.aal.domain.strategy;

import br.com.aal.domain.model.Order;

public interface PaymentStrategy {
    void pay(Order order);
}

package br.com.aal.br.com.aal.application.usecase;

import br.com.aal.domain.model.Order;
import br.com.aal.domain.strategy.PaymentStrategy;
import br.com.aal.infrastructure.factory.PaymentStrategyFactory;

public class ProcessOrderUseCase {

    private final PaymentStrategyFactory factory;

    public ProcessOrderUseCase(PaymentStrategyFactory factory) {
        this.factory = factory;
    }

    public void execute(String paymentType, Order order) {
        PaymentStrategy strategy = factory.create(paymentType);
        strategy.pay(order);
    }
}

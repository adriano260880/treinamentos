package br.com.aal.domain.strategy;

import br.com.aal.domain.model.Order;

public class CreditCardPaymentStrategy implements PaymentStrategy {
    @Override
    public void pay(Order order) {
        System.out.println("Paid with Credit Card "+order.amount());
    }
}

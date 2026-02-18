package br.com.aal.domain.strategy;

import br.com.aal.domain.model.Order;

public class PixPaymentStrategy implements PaymentStrategy {
    @Override
    public void pay(Order order) {
        System.out.println("Paid with Pix "+order.amount());
    }
}

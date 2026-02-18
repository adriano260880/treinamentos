package br.com.aal.infrastructure.factory;

import br.com.aal.domain.strategy.CreditCardPaymentStrategy;
import br.com.aal.domain.strategy.PaymentStrategy;
import br.com.aal.domain.strategy.PixPaymentStrategy;

public class PaymentStrategyFactory {
    public PaymentStrategy create(String paymentType) {
        return switch (paymentType.toLowerCase()) {
            case "credit" -> new CreditCardPaymentStrategy();
            case "pix" -> new PixPaymentStrategy();
            default -> throw new IllegalArgumentException("Invalid Payment type");
        };
    }
}

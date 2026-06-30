package br.com.adriano.model.event;

import java.math.BigDecimal;

public record OrderCreatedEvent(
        Long id,
        String customer,
        BigDecimal value
) {
}

package br.com.adriano.orders.dto;

import java.math.BigDecimal;

public record OrderCreatedEvent(
        Long id,
        String customer,
        BigDecimal value
) {
}

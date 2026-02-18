package br.com.aal.domain.model;

import java.math.BigDecimal;

public record Order (
   String id,
   BigDecimal amount
) {}

package dev.lucas.ecommerce.basketservice.Client.Response;

import java.math.BigDecimal;

public record PlatzProductResponse(Long id, String title, BigDecimal price) {
}

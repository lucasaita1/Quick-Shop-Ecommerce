package dev.lucas.ecommerce.basketservice.Client.Response;

import java.io.Serializable;
import java.math.BigDecimal;

public record PlatzProductResponse(Long id, String title, String description, BigDecimal price) implements Serializable {
}

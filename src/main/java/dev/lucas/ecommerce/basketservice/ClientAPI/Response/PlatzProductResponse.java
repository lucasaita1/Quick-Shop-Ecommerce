package dev.lucas.ecommerce.basketservice.ClientAPI.Response;

import java.io.Serializable;
import java.math.BigDecimal;

public record PlatzProductResponse(Long id, String title, String description, BigDecimal price) implements Serializable {
}

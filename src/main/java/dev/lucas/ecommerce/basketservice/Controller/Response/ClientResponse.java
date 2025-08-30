package dev.lucas.ecommerce.basketservice.Controller.Response;

import lombok.Builder;

@Builder
public record ClientResponse(String id, String name, String email) {
}

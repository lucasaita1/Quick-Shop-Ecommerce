package dev.lucas.ecommerce.basketservice.Controller.Request;



import java.util.List;

public record BasketRequest(Long clientId, List<ProductRequest> products) {
}

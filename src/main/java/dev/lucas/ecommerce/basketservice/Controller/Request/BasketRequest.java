package dev.lucas.ecommerce.basketservice.Controller.Request;



import java.util.List;

public record BasketRequest(String clientId, List<ProductRequest> products) {
}

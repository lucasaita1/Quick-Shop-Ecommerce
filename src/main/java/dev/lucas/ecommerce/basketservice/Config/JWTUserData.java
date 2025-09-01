package dev.lucas.ecommerce.basketservice.Config;


import lombok.Builder;

@Builder

public record JWTUserData (String id, String name, String email, String cpf) {

}

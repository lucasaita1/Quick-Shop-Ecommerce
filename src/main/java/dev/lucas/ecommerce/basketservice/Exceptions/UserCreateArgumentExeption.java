package dev.lucas.ecommerce.basketservice.Exceptions;

public class UserCreateArgumentExeption extends RuntimeException {
    public UserCreateArgumentExeption(String message) {
        super(message);
    }
}

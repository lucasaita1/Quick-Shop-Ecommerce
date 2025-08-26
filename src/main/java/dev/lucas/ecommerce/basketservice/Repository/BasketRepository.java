package dev.lucas.ecommerce.basketservice.Repository;

import dev.lucas.ecommerce.basketservice.Entity.Basket;
import dev.lucas.ecommerce.basketservice.Enum.Status;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasketRepository extends MongoRepository <Basket, String> {

Optional<Basket> findByClientAndStatus(Long client, Status status);
}


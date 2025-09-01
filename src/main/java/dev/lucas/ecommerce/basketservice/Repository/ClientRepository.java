package dev.lucas.ecommerce.basketservice.Repository;

import dev.lucas.ecommerce.basketservice.Entity.Client;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends MongoRepository<Client, String> {

    Optional<Client> findByEmail (String email);
}

package dev.lucas.ecommerce.basketservice.Config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import dev.lucas.ecommerce.basketservice.Entity.Client;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class TokenComponent {

    private final String secret;

    public TokenComponent() {
        Dotenv dotenv = Dotenv.load();
        this.secret = dotenv.get("SECRET");
        if (this.secret == null) {
            throw new IllegalStateException("SECRET not found in .env file");
        }
    }

    public String generateToken(Client client) {
        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
                .withSubject(client.getEmail())
                .withClaim("name", client.getName()) // corrigido
                .withClaim("cpf", client.getCpf())
                .withClaim("userId", client.getId()) // corrigido
                .withExpiresAt(Instant.now().plusSeconds(86400))
                .withIssuedAt(Instant.now())
                .sign(algorithm);
    }

    public Optional<JWTUserData> verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            DecodedJWT jwt = JWT.require(algorithm)
                    .build()
                    .verify(token);

            return Optional.of(JWTUserData.builder()
                    .id(jwt.getClaim("userId").asString()) // mesmo nome do claim
                    .name(jwt.getClaim("name").asString())
                    .email(jwt.getSubject())
                    .build());

        } catch (JWTVerificationException exception) {
            return Optional.empty();
        }
    }
}

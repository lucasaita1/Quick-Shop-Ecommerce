package dev.lucas.ecommerce.basketservice.Service;

import dev.lucas.ecommerce.basketservice.Entity.Client;
import dev.lucas.ecommerce.basketservice.Exceptions.UserCreateArgumentExeption;
import dev.lucas.ecommerce.basketservice.Repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ClientService {


    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    public Client creatClient (Client client){
        try {
            String password = client.getPassword();
            client.setPassword(passwordEncoder.encode(password));
            return clientRepository.save(client);

        } catch (Exception e) {
            if (e.getMessage().contains("email")) {
                throw new UserCreateArgumentExeption("Email already registered");
            } else if (e.getMessage().contains("cpf")) {
                throw new UserCreateArgumentExeption("CPF already registered");
            }
            throw new UserCreateArgumentExeption("Duplicate key error");
        }
        }
    }


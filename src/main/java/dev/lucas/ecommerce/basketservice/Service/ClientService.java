package dev.lucas.ecommerce.basketservice.Service;

import dev.lucas.ecommerce.basketservice.Entity.Client;
import dev.lucas.ecommerce.basketservice.Repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public Client creatClient (Client client){
        return clientRepository.save(client);
    }

}

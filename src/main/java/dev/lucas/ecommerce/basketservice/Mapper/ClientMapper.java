package dev.lucas.ecommerce.basketservice.Mapper;

import dev.lucas.ecommerce.basketservice.Controller.Request.ClientRequest;
import dev.lucas.ecommerce.basketservice.Controller.Response.ClientResponse;
import dev.lucas.ecommerce.basketservice.Entity.Client;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ClientMapper {

    public static Client toClient (ClientRequest clientRequest){

        return Client.builder()
                .name(clientRequest.name())
                .email(clientRequest.email())
                .cpf(clientRequest.cpf())
                .password(clientRequest.password())
                .build();
    }

    public static ClientResponse toResponse (Client client){

        return ClientResponse.builder()
                .id(client.getId())
                .name(client.getName())
                .email(client.getEmail())
                .build();
    }
}

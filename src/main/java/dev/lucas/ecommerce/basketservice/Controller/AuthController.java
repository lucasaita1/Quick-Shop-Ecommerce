package dev.lucas.ecommerce.basketservice.Controller;

import dev.lucas.ecommerce.basketservice.Controller.Request.ClientRequest;
import dev.lucas.ecommerce.basketservice.Controller.Response.ClientResponse;
import dev.lucas.ecommerce.basketservice.Entity.Client;
import dev.lucas.ecommerce.basketservice.Mapper.ClientMapper;
import dev.lucas.ecommerce.basketservice.Service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/client/auth")
@RequiredArgsConstructor
public class AuthController {

    private final ClientService service;

    @PostMapping ("/register")
    public ResponseEntity<ClientResponse> createClient (@RequestBody ClientRequest request){
        Client client = ClientMapper.toClient(request);
        Client newClient = service.creatClient(client);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ClientMapper.toResponse(client));
    }
}

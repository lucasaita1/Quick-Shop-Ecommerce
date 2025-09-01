package dev.lucas.ecommerce.basketservice.Controller;

import dev.lucas.ecommerce.basketservice.Config.TokenComponent;
import dev.lucas.ecommerce.basketservice.Controller.Request.ClientRequest;
import dev.lucas.ecommerce.basketservice.Controller.Request.LoginRequest;
import dev.lucas.ecommerce.basketservice.Controller.Response.ClientResponse;
import dev.lucas.ecommerce.basketservice.Entity.Client;
import dev.lucas.ecommerce.basketservice.Mapper.ClientMapper;
import dev.lucas.ecommerce.basketservice.Service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/client/auth")
@RequiredArgsConstructor
public class AuthController {

    private final ClientService service;
    private final AuthenticationManager authenticationManager;
    private final TokenComponent tokenComponent;

    @PostMapping ("/register")
    public ResponseEntity<ClientResponse> createClient (@RequestBody ClientRequest request){
        Client client = ClientMapper.toClient(request);
        Client newClient = service.creatClient(client);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ClientMapper.toResponse(client));
    }

    @PostMapping ("/login")
    public ResponseEntity<String> login (@RequestBody LoginRequest loginRequest){
        try {
            UsernamePasswordAuthenticationToken userAndPassword = new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password());
            Authentication authentication = authenticationManager.authenticate(userAndPassword);

            Client client = (Client) authentication.getPrincipal();
            String token = tokenComponent.generateToken(client);

            return ResponseEntity.ok(token);
        } catch (BadCredentialsException exception){
            throw new UsernameNotFoundException("User or password incorrects");
        }

    }
}

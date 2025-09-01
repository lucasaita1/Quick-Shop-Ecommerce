package dev.lucas.ecommerce.basketservice.Controller.Documentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import dev.lucas.ecommerce.basketservice.Controller.Request.ClientRequest;
import dev.lucas.ecommerce.basketservice.Controller.Request.LoginRequest;
import dev.lucas.ecommerce.basketservice.Controller.Response.ClientResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Autenticação", description = "APIs para registro e login de clientes")
public interface AuthControllerSwagger {

    @Operation(summary = "Registrar um novo cliente", description = "Cria um novo registro de cliente no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente registrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClientResponse.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{message: Erro de validação}")))
    })
    ResponseEntity<ClientResponse> createClient(@RequestBody ClientRequest request);

    @Operation(summary = "Autenticar cliente", description = "Realiza o login de um cliente e retorna um token de autenticação.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login bem-sucedido",
                    content = @Content(mediaType = "text/plain",
                            schema = @Schema(type = "string", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."))),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{message: Usuário ou senha incorretos}")))
    })
    ResponseEntity<String> login(@RequestBody LoginRequest loginRequest);
}


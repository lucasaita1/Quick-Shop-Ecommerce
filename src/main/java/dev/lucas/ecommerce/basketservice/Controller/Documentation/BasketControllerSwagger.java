package dev.lucas.ecommerce.basketservice.Controller.Documentation;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import dev.lucas.ecommerce.basketservice.Controller.Request.BasketRequest;
import dev.lucas.ecommerce.basketservice.Controller.Request.PaymentRequest;
import dev.lucas.ecommerce.basketservice.Entity.Basket;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Cesta de Compras", description = "APIs para gerenciamento de cestas de compras")
public interface BasketControllerSwagger {

    @Operation(summary = "Obter cesta por ID", description = "Retorna uma cesta de compras específica pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cesta encontrada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Basket.class))),
            @ApiResponse(responseCode = "404", description = "Cesta não encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\"message\": \"Cesta não encontrada\"}")))
    })
    ResponseEntity<Basket> basketById(@PathVariable String id);

    @Operation(summary = "Obter cestas de um cliente", description = "Retorna todas as cestas de compras associadas a um cliente específico pelo ID do cliente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cestas encontradas com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Basket.class))),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado ou sem cestas",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\"message\": \"Cliente não encontrado ou sem cestas\"}")))
    })
    ResponseEntity<List<Basket>> getBasketsFromClient(@PathVariable String id);

    @Operation(summary = "Criar nova cesta", description = "Cria uma nova cesta de compras.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cesta criada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Basket.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\"message\": \"Erro de validação\"}")))
    })
    ResponseEntity<Basket> createBasket(@RequestBody BasketRequest request);

    @Operation(summary = "Atualizar cesta", description = "Atualiza uma cesta de compras existente pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cesta atualizada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Basket.class))),
            @ApiResponse(responseCode = "404", description = "Cesta não encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\"message\": \"Cesta não encontrada\"}")))
    })
    ResponseEntity<Basket> updateBasket(@PathVariable String id, @RequestBody BasketRequest request);

    @Operation(summary = "Pagar cesta", description = "Processa o pagamento de uma cesta de compras específica.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pagamento processado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Basket.class))),
            @ApiResponse(responseCode = "400", description = "Requisição de pagamento inválida",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\"message\": \"Erro no pagamento\"}"))),
            @ApiResponse(responseCode = "404", description = "Cesta não encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\"message\": \"Cesta não encontrada\"}")))
    })
    ResponseEntity<Basket> payBasket(@PathVariable String id, @RequestBody PaymentRequest request);
}


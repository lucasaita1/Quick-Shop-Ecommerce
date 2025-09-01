package dev.lucas.ecommerce.basketservice.Controller.Documentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import dev.lucas.ecommerce.basketservice.ClientAPI.Response.PlatzProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Tag(name = "Produtos", description = "APIs para consulta de produtos")
public interface ProductControllerSwagger {

    @Operation(summary = "Obter todos os produtos", description = "Retorna uma lista de todos os produtos disponíveis.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de produtos retornada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PlatzProductResponse.class)))
    })
    ResponseEntity<List<PlatzProductResponse>> getAllProduct();

    @Operation(summary = "Obter produto por ID", description = "Retorna um produto específico pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto encontrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PlatzProductResponse.class))),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\"message\": \"Produto não encontrado\"}")))
    })
    ResponseEntity<PlatzProductResponse> getProductById(@PathVariable Long id);
}


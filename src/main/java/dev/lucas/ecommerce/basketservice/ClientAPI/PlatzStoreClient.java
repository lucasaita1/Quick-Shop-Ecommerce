package dev.lucas.ecommerce.basketservice.ClientAPI;


import dev.lucas.ecommerce.basketservice.ClientAPI.Response.PlatzProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "PlatzStoreClient", url = "https://api.escuelajs.co/api/v1")
public interface PlatzStoreClient {

    @GetMapping("/products")
    List<PlatzProductResponse> getAllProduct ();

    @GetMapping("/products/{id}")
    PlatzProductResponse getProductById (@PathVariable Long id);
}

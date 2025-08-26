package dev.lucas.ecommerce.basketservice.Service;


import dev.lucas.ecommerce.basketservice.Client.PlatzStoreClient;
import dev.lucas.ecommerce.basketservice.Client.Response.PlatzProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final PlatzStoreClient platzStoreClient;

    @Cacheable(value = "products")
    public List<PlatzProductResponse> getAllProduct(){
        log.info("Getting all products");
       return platzStoreClient.getAllProduct();
    }

    @Cacheable(value = "poduct", key = "#productId")
    public PlatzProductResponse getProductById (Long productId){
        log.info("Getting product with id: {}", productId);
        return platzStoreClient.getProductById(productId);
    }
}

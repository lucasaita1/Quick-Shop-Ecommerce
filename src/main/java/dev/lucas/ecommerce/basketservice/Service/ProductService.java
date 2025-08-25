package dev.lucas.ecommerce.basketservice.Service;


import dev.lucas.ecommerce.basketservice.Client.PlatzStoreClient;
import dev.lucas.ecommerce.basketservice.Client.Response.PlatzProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final PlatzStoreClient platzStoreClient;

    public List<PlatzProductResponse> getAllProduct(){
       return platzStoreClient.getAllProduct();
    }

    public PlatzProductResponse getProductById (Long id){
        return platzStoreClient.getProductById(id);
    }
}

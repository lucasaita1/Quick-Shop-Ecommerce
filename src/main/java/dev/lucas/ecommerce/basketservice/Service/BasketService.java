package dev.lucas.ecommerce.basketservice.Service;

import dev.lucas.ecommerce.basketservice.Client.Response.PlatzProductResponse;
import dev.lucas.ecommerce.basketservice.Controller.Request.BasketRequest;
import dev.lucas.ecommerce.basketservice.Entity.Basket;
import dev.lucas.ecommerce.basketservice.Entity.Product;
import dev.lucas.ecommerce.basketservice.Enum.Status;
import dev.lucas.ecommerce.basketservice.Repository.BasketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasketService {

    private final BasketRepository basketRepository;
    private final ProductService productService;

    public Optional<Basket> getBasketById(String id) {
        Optional<Basket> basket = basketRepository.findById(id);
        if (basket.isEmpty()) {
            throw new IllegalArgumentException("Basket not found with id: " + id);
        }
        return basket;
    }

    public Basket createBasket (BasketRequest basketRequest){
        basketRepository.findByClientAndStatus(basketRequest.clientId(), Status.OPEN)
                .ifPresent(basket -> {
                    throw new IllegalStateException("There is already an open basket for this client");
                });

        List<Product> arrayProducts = new ArrayList<>();
        basketRequest.products().forEach(productRequest -> {
            PlatzProductResponse platzProductResponse = productService.getProductById(productRequest.id());
            arrayProducts.add(Product.builder()
                    .id(platzProductResponse.id())
                    .title(platzProductResponse.title())
                    .price(platzProductResponse.price())
                    .quantity(productRequest.quantity())
                    .build());
        });

        Basket basket = Basket.builder()
                .client(basketRequest.clientId())
                .status(Status.OPEN)
                .products(arrayProducts)
                .build();
        basket.calculeteTotalPrice();
        return basketRepository.save(basket);

    }
}

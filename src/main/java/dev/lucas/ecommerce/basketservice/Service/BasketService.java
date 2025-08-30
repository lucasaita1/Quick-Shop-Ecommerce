package dev.lucas.ecommerce.basketservice.Service;

import dev.lucas.ecommerce.basketservice.Client.Response.PlatzProductResponse;
import dev.lucas.ecommerce.basketservice.Controller.Request.BasketRequest;
import dev.lucas.ecommerce.basketservice.Controller.Request.PaymentRequest;
import dev.lucas.ecommerce.basketservice.Entity.Basket;
import dev.lucas.ecommerce.basketservice.Entity.Client;
import dev.lucas.ecommerce.basketservice.Entity.Product;
import dev.lucas.ecommerce.basketservice.Enum.Status;
import dev.lucas.ecommerce.basketservice.Exceptions.BusinessException;
import dev.lucas.ecommerce.basketservice.Exceptions.DataNotFoundException;
import dev.lucas.ecommerce.basketservice.Repository.BasketRepository;
import dev.lucas.ecommerce.basketservice.Repository.ClientRepository;
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
    private final ClientRepository clientRepository;

    public Basket getBasketById (String id){
        return basketRepository.findById(id).orElseThrow(()-> new DataNotFoundException("Basket not found"));
    }

    public Basket createBasket (BasketRequest basketRequest){
        basketRepository.findByClientAndStatus(basketRequest.clientId(), Status.OPEN)
                .ifPresent(basket -> {
                    throw new BusinessException("There is already an open basket for this client");
                });

        List<Product> arrayProducts = getProducts(basketRequest);

        Basket basket = Basket.builder()
                .client(basketRequest.clientId())
                .status(Status.OPEN)
                .products(arrayProducts)
                .build();
        basket.calculeteTotalPrice();
        return basketRepository.save(basket);
    }

    public Basket updateBasket (String basketId, BasketRequest basketRequest) {
        Basket savedbasket = getBasketById(basketId);
        List<Product> arrayProducts = getProducts(basketRequest);

        savedbasket.setProducts(arrayProducts);
        savedbasket.calculeteTotalPrice();
        return basketRepository.save(savedbasket);

    }

    public Basket payBasket (String basketId, PaymentRequest paymentRequest){
        Basket savedbasket = getBasketById(basketId);
        savedbasket.setPaymentMethod(paymentRequest.getPaymentMethod());
        savedbasket.setStatus(Status.CLOSE);
        return basketRepository.save(savedbasket);
    }

    public List<Basket> getBasketFromClient (String clientId){
        Optional<Client> client = clientRepository.findById(clientId);
        if (client.isEmpty()){
            throw new DataNotFoundException("Client not found with id: " + clientId);
        }
        return basketRepository.findByClient(clientId);
    }

    private List<Product> getProducts(BasketRequest basketRequest) {
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
        return arrayProducts;
    }
}

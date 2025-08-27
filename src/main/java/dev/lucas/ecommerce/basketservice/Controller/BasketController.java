package dev.lucas.ecommerce.basketservice.Controller;

import dev.lucas.ecommerce.basketservice.Controller.Request.BasketRequest;
import dev.lucas.ecommerce.basketservice.Entity.Basket;
import dev.lucas.ecommerce.basketservice.Service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/basket")
public class BasketController {

    private final BasketService service;

    @GetMapping("/{id}")
    public ResponseEntity<Basket> basketById(@PathVariable String id) {
        return ResponseEntity.ok().body(service.getBasketById(id));
    }

    @PostMapping
    public ResponseEntity<Basket> createBasket(@RequestBody BasketRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createBasket(request));
    }

    @PutMapping
    public ResponseEntity<Basket> updateBasket(@PathVariable String id, @RequestBody BasketRequest request){
        return ResponseEntity.ok().body(service.updateBasket(id, request));
    }

}

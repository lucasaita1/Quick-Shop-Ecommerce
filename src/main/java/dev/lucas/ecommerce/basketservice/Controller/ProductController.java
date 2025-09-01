package dev.lucas.ecommerce.basketservice.Controller;

import dev.lucas.ecommerce.basketservice.ClientAPI.Response.PlatzProductResponse;
import dev.lucas.ecommerce.basketservice.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {


  private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<PlatzProductResponse>> getAllProduct() {
        return ResponseEntity.ok(productService.getAllProduct());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlatzProductResponse> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }
}

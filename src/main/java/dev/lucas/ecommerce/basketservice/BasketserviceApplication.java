package dev.lucas.ecommerce.basketservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "client")
public class BasketserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BasketserviceApplication.class, args);
    }
}
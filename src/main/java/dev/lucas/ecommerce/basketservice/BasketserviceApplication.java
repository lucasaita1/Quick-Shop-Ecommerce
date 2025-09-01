package dev.lucas.ecommerce.basketservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableCaching
@EnableFeignClients(basePackages = "dev.lucas.ecommerce.basketservice.clientAPI")
public class BasketserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BasketserviceApplication.class, args);
    }
}
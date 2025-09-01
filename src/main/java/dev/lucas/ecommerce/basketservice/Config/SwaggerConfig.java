package dev.lucas.ecommerce.basketservice.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI getOpenAPIO() {
        Contact contact = new Contact()
                .name("Lucas Aita")
                .email("lucasaita4000@gmail.com");

        Info info = new Info()
                .title("BasketService API")
                .version("v1")
                .description("API para gerenciamento de cestas de compras em um sistema de e-commerce.")
                .contact(contact);

        return new OpenAPI().info(info);
    }
}

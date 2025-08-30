package dev.lucas.ecommerce.basketservice.Entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document (collection = "clients")
public class Client {

    @Id
    private String id;

    private String name;

    private String email;

    private String cpf;

    private String password;


}

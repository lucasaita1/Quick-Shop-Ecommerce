package dev.lucas.ecommerce.basketservice.Controller.Request;

import dev.lucas.ecommerce.basketservice.Enum.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PaymentRequest {

    private PaymentMethod paymentMethod;
}

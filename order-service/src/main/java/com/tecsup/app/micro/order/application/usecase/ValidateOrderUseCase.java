package com.tecsup.app.micro.order.application.usecase;

import com.tecsup.app.micro.order.domain.exception.InvalidOrderDataException;
import com.tecsup.app.micro.order.domain.model.Order;
import com.tecsup.app.micro.order.domain.model.OrderItem;
import com.tecsup.app.micro.order.domain.model.Product;
import com.tecsup.app.micro.order.domain.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
@Slf4j
public class ValidateOrderUseCase {

    private final GetProductByIdUseCase getProductByIdUseCase;
    private final GetUserByEmailUseCase getUserByEmailUseCase;

    public Order validate(Order order,String email, String token) {
        log.debug("Inicio validacion de Orden");
        if(email!=null) {
            User cliente = getUserByEmailUseCase.execute(email, token);
            if (cliente == null) {
                throw new InvalidOrderDataException("El email no existe");
            } else {
                order.setUserId(cliente.getId());
                if (order.getItems() != null) {
                    for (OrderItem item : order.getItems()) {
                        Product product = getProductByIdUseCase.execute(item.getProductId(), token);
                        if (product != null && product.isAvailable(product.getStock())) {
                            item.setUnitPrice(product.getPrice());
                            item.setProduct(product);
                        } else {
                            throw new InvalidOrderDataException("Orden Item tiene producto invalido: "
                                    + item.getProductId());
                        }
                    }
                }
            }
        }else{
            throw new InvalidOrderDataException("El usuario asociado al token no tiene email");
        }
        return order;

    }


}

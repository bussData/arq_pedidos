package com.tecsup.app.micro.order.application.usecase;

import com.tecsup.app.micro.order.domain.exception.InvalidOrderDataException;
import com.tecsup.app.micro.order.domain.model.Order;
import com.tecsup.app.micro.order.domain.model.OrderItem;
import com.tecsup.app.micro.order.domain.model.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
@Slf4j
public class ValidateOrderUseCase {

    private final GetProductByIdUseCase getProductByIdUseCase;

    public Order validate(Order order,String token) {
        log.debug("Inicio validacion de Orden");

        if (order.getItems() != null) {
            for (OrderItem item : order.getItems()) {
                Product product = getProductByIdUseCase.execute(item.getProductId(), token);
                if(product!=null && product.isAvailable(product.getStock())) {
                    item.setUnitPrice(product.getPrice());
                    item.setProduct(product);
                } else{
                    throw new InvalidOrderDataException("Orden Item tiene producto invalido: "+item.getProductId());
                }
            }
        }

        return order;

    }


}

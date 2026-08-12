package com.tecsup.app.micro.order.application.service;

import com.tecsup.app.micro.order.domain.model.Order;
import com.tecsup.app.micro.order.application.usecase.*;
import com.tecsup.app.micro.order.domain.model.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tecsup.app.micro.order.infrastructure.web.dto.*;

import java.util.List;

/**
 * Servicio de Aplicación de Producto
 * Orquesta los casos de uso y maneja las transacciones
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class OrderApplicationService {

    private final CreateOrderUseCase createOrderUseCase;
    private final GetProductByIdUseCase getProductByIdUseCase;
    private final ValidateOrderUseCase validateOrderUseCase;

    /*
    @Transactional(readOnly = true)
   public List<Order> getAllOrders() {
        return getAllOrdersUseCase.execute();
    }*/

    @Transactional(readOnly = true)
    public Product getProductById(Long productId,  String jwtToken) {
        return getProductByIdUseCase.execute(productId, jwtToken);
    }

    @Transactional
    public Order validateOrder(Order order, String email, String token) {
        return validateOrderUseCase.validate(order,email, token);
    }


    @Transactional
    public Order createOrder(Order order, String token) {
        return createOrderUseCase.execute(order,token);
    }

}

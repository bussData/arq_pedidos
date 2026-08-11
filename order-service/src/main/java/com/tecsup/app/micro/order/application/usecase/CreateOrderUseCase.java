package com.tecsup.app.micro.order.application.usecase;

import com.tecsup.app.micro.order.domain.model.Order;
import com.tecsup.app.micro.order.domain.model.OrderItem;
import com.tecsup.app.micro.order.domain.repository.OrderItemRepository;
import com.tecsup.app.micro.order.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

/**
 * Caso de uso: Crear un nuevo producto
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class CreateOrderUseCase {
    
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public Order execute(Order order) {
        log.debug("Executing CreateOrderUseCase for product: {}", order.getOrderNumber());
        // Guardar orden
        BigDecimal total = new BigDecimal(0);
        if (order.getItems() != null) {
            for (OrderItem item : order.getItems()) {
                BigDecimal subtotal =item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
                item.setSubtotal(subtotal);
                total = total.add(item.getSubtotal());
            }
        }

        order.setStatus("PENDING");
        //seteo de nro de orden con la secuencia de bd:
        Long nextId = orderRepository.getNextOrderNumber();
        String numOrder = "ORD-"+ LocalDate.now().getYear()+"-"+nextId;
        order.setOrderNumber(numOrder);
        //Seteo de valor total y grabado de cabecera:
        order.setTotalAmount(total);

        Order savedOrder = orderRepository.save(order);

        List<OrderItem> lstItemsSaved = new ArrayList<OrderItem>();
        for (OrderItem item : order.getItems()) {
            item.setOrderId(savedOrder.getId());
            OrderItem itemSaved = orderItemRepository.save(item);
            itemSaved.setProduct(item.getProduct());
            lstItemsSaved.add(itemSaved);
        }
        savedOrder.setItems(lstItemsSaved);
       log.info("Order created successfully with id: {}", savedOrder.getOrderNumber());
        
        return savedOrder;
    }
}

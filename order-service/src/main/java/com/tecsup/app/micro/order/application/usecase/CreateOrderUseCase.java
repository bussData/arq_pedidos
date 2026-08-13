package com.tecsup.app.micro.order.application.usecase;

import com.tecsup.app.micro.order.domain.model.Order;
import com.tecsup.app.micro.order.domain.model.OrderItem;
import com.tecsup.app.micro.order.domain.model.Product;
import com.tecsup.app.micro.order.domain.repository.OrderItemRepository;
import com.tecsup.app.micro.order.domain.repository.OrderRepository;
import com.tecsup.app.micro.order.shared.infraestructure.event.KafkaEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import com.tecsup.app.micro.events.OrderCreatedEvent;
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
    private final UpdateProductByIdUseCase updateProductByIdUseCase;

    private final KafkaEventPublisher eventPublisher;

    public Order execute(Order order, String token) {
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

            Product productNew = updateProductByIdUseCase.execute(item.getProduct().getId(),
                    item.getQuantity(),token.replace("Bearer ",""));
            if(productNew!=null){
                log.info("se actualizo el stock del producto");
            }
        }
        savedOrder.setItems(lstItemsSaved);
        log.info("Order created successfully with id: {}", savedOrder.getOrderNumber());

        //Crea el evento Kafka
        OrderCreatedEvent event =
                new OrderCreatedEvent(savedOrder.getId().toString(),savedOrder.getUserId().toString(),
                        savedOrder.getStatus() );
        this.eventPublisher.publish(event);

        return savedOrder;
    }
}

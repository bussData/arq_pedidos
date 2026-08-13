package com.tecsup.app.micro.order.application.usecase;

import com.tecsup.app.micro.events.OrderUpdatedEvent;
import com.tecsup.app.micro.order.domain.exception.OrderNotFoundException;
import com.tecsup.app.micro.order.domain.model.Order;
import com.tecsup.app.micro.order.domain.repository.OrderRepository;
import com.tecsup.app.micro.order.shared.infraestructure.event.KafkaEventPublisher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateOrderStatusUseCase {

    private final KafkaEventPublisher eventPublisher;
    private final OrderRepository orderRepository;

    public Order updateOrderStatus(String orderId, String status) {
        Order ordenActualizable = orderRepository.findById(Long.valueOf(orderId))
                .orElseThrow(() -> new OrderNotFoundException(orderId));
        ordenActualizable.setStatus(status);
        orderRepository.save(ordenActualizable);

        //Crear el evento kafka
        OrderUpdatedEvent event =
                new OrderUpdatedEvent(ordenActualizable.getId().toString(),
                        ordenActualizable.getUserId().toString(),
                        ordenActualizable.getStatus() );
        this.eventPublisher.publish(event);

        return ordenActualizable;
    }
}

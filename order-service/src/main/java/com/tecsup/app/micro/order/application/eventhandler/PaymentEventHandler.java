package com.tecsup.app.micro.order.application.eventhandler;

import com.tecsup.app.micro.events.DomainEvent;
import com.tecsup.app.micro.events.PaymentApprovedEvent;
import com.tecsup.app.micro.events.PaymentRejectedEvent;
import com.tecsup.app.micro.order.application.usecase.UpdateOrderStatusUseCase;
import com.tecsup.app.micro.order.shared.infraestructure.config.KafkaConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PaymentEventHandler {

    private final UpdateOrderStatusUseCase updateOrderStatusUseCase;

    public PaymentEventHandler(UpdateOrderStatusUseCase updateOrderStatusUseCase) {
        this.updateOrderStatusUseCase = updateOrderStatusUseCase;
    }

    @KafkaListener(
            topics = KafkaConfig.PAYMENT_PROCESSED_TOPIC,
            groupId = "order-group"         // Grupo de consumidores
    )


    public void handleOrderEvents(DomainEvent event) {
        if (event instanceof PaymentApprovedEvent) {
            this.handleOrderUpdateStatusApproved((PaymentApprovedEvent) event);
        }  else if (event instanceof PaymentRejectedEvent) {
            this.handleOrderUpdateStatusRejected((PaymentRejectedEvent) event);
        } else {
            throw new RuntimeException("Invalid event type " + event.getClass());
        }
    }


    public void handleOrderUpdateStatusApproved(PaymentApprovedEvent event)
    {
        log.info("Pago aprobado {}", event);
        updateOrderStatusUseCase.updateOrderStatus(String.valueOf(event.getOrderId()),"CONFIRMED");
        log.info("[Kafka] PAGO de matricula confirmado: {}", event);

    }


    public void handleOrderUpdateStatusRejected(PaymentRejectedEvent  event)
    {

        updateOrderStatusUseCase.updateOrderStatus(String.valueOf(event.getOrderId()),"CANCELED");
        log.info("[Kafka] PAGO de matricula rechazado: {}", event);

    }

}

package com.tecsup.app.micro.delivery.application.eventhandler;

import com.tecsup.app.micro.delivery.application.usecase.CreateDeliveryUseCase;
import com.tecsup.app.micro.delivery.domain.model.Delivery;
import com.tecsup.app.micro.delivery.infraestructure.config.KafkaConfig;
import com.tecsup.app.micro.delivery.infraestructure.event.KafkaEventPublisher;
import com.tecsup.app.micro.events.DeliveryCreateEvent;
import com.tecsup.app.micro.events.DomainEvent;
import com.tecsup.app.micro.events.PaymentRejectedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import com.tecsup.app.micro.events.PaymentApprovedEvent;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Slf4j
@Service
@RequiredArgsConstructor
public class DeliveryEventHandler {

    private final CreateDeliveryUseCase createDeliveryUseCase;
    private final KafkaEventPublisher kafkaEventPublisher;

    @KafkaListener(
            topics = KafkaConfig.PAYMENT_PROCESSED_TOPIC,
            groupId = "delivery-group"         // Grupo de consumidores
    )

    public void handleDeliveryEvents(DomainEvent event) {
        if(event instanceof PaymentApprovedEvent){
            this.handleDeliveryCreateRecord((PaymentApprovedEvent) event);
        }else if(event instanceof PaymentRejectedEvent) {
            log.info("Received Payment Rejected Event, no se registra delivery");
        }else{
            throw new RuntimeException("Invalid event type " + event.getClass());
        }
    }

    public  void handleDeliveryCreateRecord(PaymentApprovedEvent event) {
        log.info("Pago aprobado {}", event);
        Delivery deliver = createDeliveryUseCase.create(String.valueOf(event.getOrderId()),"CREATED");
        log.info("[Kafka] PAGO de Orden confirmado, se registra el delivery para asignacion de funcionario: {}", event);

        DeliveryCreateEvent deliveryEvent = new DeliveryCreateEvent(
                deliver.getOrderId(),
                deliver.getTraceCode(),
                deliver.getStatus(),
                LocalDateTime.now());
        kafkaEventPublisher.publish(deliveryEvent);
    }
}

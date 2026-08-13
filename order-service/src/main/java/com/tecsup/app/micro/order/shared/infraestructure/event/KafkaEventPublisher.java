package com.tecsup.app.micro.order.shared.infraestructure.event;


import com.tecsup.app.micro.events.OrderCreatedEvent;
import com.tecsup.app.micro.events.OrderUpdatedEvent;
import com.tecsup.app.micro.order.shared.infraestructure.config.KafkaConfig;
import com.tecsup.app.micro.events.DomainEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.kafka.core.KafkaTemplate;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaEventPublisher {
    private final KafkaTemplate<String, DomainEvent> kafkaTemplate;

    public void publish(DomainEvent event) {

        log.info("Publishing {}", event);

        String topic = getTopicFromEvent(event);

        String key = event.getKey();

        //

        this.kafkaTemplate.send(
                topic,
                key,
                event);

    }

    private String getTopicFromEvent(DomainEvent event) {

        if (event instanceof OrderCreatedEvent) {  // AGREGAR
            return KafkaConfig.ORDER_EVENTS_TOPIC;
        }else if (event instanceof OrderUpdatedEvent) {  // AGREGAR
            return KafkaConfig.ORDER_UPDATE_TOPIC;
        }else{
            throw new IllegalArgumentException("Unknown event type: " + event.getEventType());
        }
    }

}

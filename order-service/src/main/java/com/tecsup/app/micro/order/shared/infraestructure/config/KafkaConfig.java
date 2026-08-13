package com.tecsup.app.micro.order.shared.infraestructure.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;

@EnableKafka
@Configuration
public class KafkaConfig {


    public static final String ORDER_EVENTS_TOPIC = "order.events";
    public static final String ORDER_UPDATE_TOPIC = "order.events";
    public static final String PAYMENT_PROCESSED_TOPIC = "payment.events";
    public static final String PAYMENT_FAILED_TOPIC = "payment.events";

    @Bean
    public NewTopic paymentProcessedTopic() {
        return TopicBuilder
                .name(PAYMENT_PROCESSED_TOPIC)
                .partitions(3)
                .replicas(1)
                .build();
    }


    // NUEVO BEAN
    @Bean
    public NewTopic paymentFailedTopic() {
        return TopicBuilder
                .name(PAYMENT_FAILED_TOPIC)
                .partitions(3)
                .replicas(1)
                .build();
    }
}

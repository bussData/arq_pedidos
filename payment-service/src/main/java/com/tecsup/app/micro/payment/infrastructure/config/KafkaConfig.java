package com.tecsup.app.micro.payment.infrastructure.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;

@EnableKafka
@Configuration
public class KafkaConfig {

    // DLQ
   // public static final String DLQ_COURSE_EVENTS_TOPIC = "dlq.course.events";  // ✅ DLQ Topic


    // SAGA
   // public static final String ENROLLMENT_REQUEST_TOPIC = "enrollment.requested";

    public static final String PAYMENT_PROCESSED_TOPIC = "payment.events";   // AGREGAR
    //public static final String PAYMENT_FAILED_TOPIC = "payment.failed";  // AGREGAR

    public static final String ENROLLMENT_UPDATE_TOPIC = "enrollment.events";


    // Set QUEUES/PARTITIONS

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
                .name(PAYMENT_PROCESSED_TOPIC)
                .partitions(3)
                .replicas(1)
                .build();
    }

}

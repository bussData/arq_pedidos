package com.tecsup.app.micro.delivery.domain.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Delivery {

    private String id;
    private Long orderId;
    private String status;
    private String traceCode;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static Delivery create( Long orderId, String status)
    {
        String deliveryId = "traceCode-" + UUID.randomUUID();

        return Delivery.builder()
                .orderId(orderId)
                .status(status)
                .traceCode(deliveryId)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}

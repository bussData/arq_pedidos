package com.tecsup.app.micro.delivery.infraestructure.persistence;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "deliveries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryEntity {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "payment_transaction_generator"
    )
    @SequenceGenerator(
            name = "payment_transaction_generator",
            sequenceName = "seq_deliveries",
            allocationSize = 1
    )
    private Long id;

    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "trace_code", nullable = false)
    private String traceCode;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}

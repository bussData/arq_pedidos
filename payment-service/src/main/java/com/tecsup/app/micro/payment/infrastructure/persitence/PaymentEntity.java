package com.tecsup.app.micro.payment.infrastructure.persitence;

import com.tecsup.app.micro.payment.domain.model.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentEntity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "payment_generator"
    )
    @SequenceGenerator(
            name = "payment_generator",
            sequenceName = "seq_payments",
            allocationSize = 1
    )
    private Long id;

    @Column(name = "order_id", nullable = false)
    private Long orderId;

    private String  transactionId;

    @Column(nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status;

    @Column(name = "paid_at")
    private LocalDateTime paidAt;
}

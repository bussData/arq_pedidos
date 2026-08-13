package com.tecsup.app.micro.payment.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    private String id;

    private Long orderId;

    private BigDecimal amount;

    private PaymentStatus status;

    private String transactionId;

    private LocalDateTime paidAt;

    /**
     * Crea un pago en estado PENDING.
     */
    public static Payment create(Long orderId, BigDecimal amount) {

        return Payment.builder()
                //.id(UUID.randomUUID().toString())
                .orderId(orderId)
                .amount(amount)
                .status(PaymentStatus.PENDING)
                .paidAt(LocalDateTime.now())
                .build();
    }

    /**
     * Aprobar el pago.
     */
    public void approve(String transactionId) {
        this.status = PaymentStatus.APPROVED;
        this.transactionId = transactionId;
    }

    /**
     * Rechazar el pago.
     */
    public void reject() {
        this.status = PaymentStatus.REJECTED;
    }
}

package com.tecsup.app.micro.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRejectedEvent extends DomainEvent {

    private Long orderId;
    private String reason;
    private String errorCode;
    private LocalDateTime timestamp;

    @Override
    public String getKey() {
        return orderId.toString();
    }
}

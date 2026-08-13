package com.tecsup.app.micro.payment.application.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePaymentCommand {
    private Long orderId;

    private BigDecimal amount;

}

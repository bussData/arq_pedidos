package com.tecsup.app.micro.payment.domain.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentTransaction {
    private String id;
    private Long paymentId;
    private String transactionId;
    private BigDecimal amount;
    private String failureReason;
    private LocalDateTime createdAt;
    private String status;


    public static PaymentTransaction create( Long paymentId,  BigDecimal amount, String failureReason,
                                            String failureMessage, String transactionId ) {

        if(failureReason!=null && !failureReason.equals("")){
            return  PaymentTransaction.builder()
                    //.id(UUID.randomUUID().toString())
                    .paymentId(paymentId)
                    .amount(amount)
                    .status(String.valueOf(PaymentStatus.REJECTED))
                    .transactionId(transactionId)
                    .failureReason(failureMessage)
                    .createdAt(LocalDateTime.now())
                    .build();
        }else{
            return  PaymentTransaction.builder()
                    //.id(UUID.randomUUID().toString()
                    .paymentId(paymentId)
                    .transactionId(transactionId)
                    .amount(amount)
                    .status(String.valueOf(PaymentStatus.APPROVED))
                    .createdAt(LocalDateTime.now())
                    .build();
        }

    }

}

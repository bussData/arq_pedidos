package com.tecsup.app.micro.order.domain.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class OrderItem {

    private Long id;
    private Long orderId;
    private Long productId;
    private Product product;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal subtotal;
}

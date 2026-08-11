package com.tecsup.app.micro.order.infrastructure.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemResponse {

    private Long id;

    //private Long productId;
    private ProductResponse product;

    private Integer quantity;

    private BigDecimal unitPrice;

    private BigDecimal subtotal;
}
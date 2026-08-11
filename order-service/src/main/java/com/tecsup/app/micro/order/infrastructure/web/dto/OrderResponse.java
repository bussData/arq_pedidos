package com.tecsup.app.micro.order.infrastructure.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO de respuesta de order
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private Long id;
    private String orderNumber;
    private Long userId;
    private List<OrderItemResponse> items;
    private String status;
    private BigDecimal totalAmount;
    private LocalDateTime createdAt;

}

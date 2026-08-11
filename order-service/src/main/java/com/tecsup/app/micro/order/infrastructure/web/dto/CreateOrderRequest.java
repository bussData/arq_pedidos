package com.tecsup.app.micro.order.infrastructure.web.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * DTO para crear un producto
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {

    @NotNull(message = "userId is required")
    private Long userId;

    @Valid
    @NotEmpty(message = "Order must contain at least one item")
    private List<CreateOrderItemRequest> items;
}

package com.tecsup.app.micro.order.infrastructure.web.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO para actualizar un producto
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderRequest {

    private Long orderNumber;
    private String status;
    
    @NotNull(message = "total is required")
    @DecimalMin(value = "0.0", message = "total must be positive or zero")
    private BigDecimal totalAmount;

}

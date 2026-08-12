package com.tecsup.app.micro.catalog.infraestructure.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private Long id;
    private CategoryResponse category;
    private String name;
    private String unitcode;
    private BigDecimal price;
    private int stock;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private RestaurantResponse restaurant;
}

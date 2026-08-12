package com.tecsup.app.micro.order.infrastructure.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {

    private Long id;
    private String name;
    private String unitcode;
    private BigDecimal price;
    private Integer stock;
    //private String categoryId;
    private String createdBy;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private CategoryDTO category;
    private RestaurantDTO restaurant;

    public ProductoDTO(Long id, String name,
                       BigDecimal price, Integer stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
}

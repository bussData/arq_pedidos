package com.tecsup.app.micro.order.domain.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Product Domain Model (Core Business Entity)
 * Esta es la entidad de dominio pura, sin dependencias de frameworks
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class Product {
    
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private String category;
    private Long createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private User createdByUser; // Relación con el usuario que creó el producto (opcional)

    public Product(Long id, String name, BigDecimal bigDecimal, int i) {
        this.id = id;
        this.name = name;
        this.price = bigDecimal;
        this.stock = i;
    }

    /**
     * Valida que el producto tenga los datos mínimos requeridos
     */
    public boolean isValid() {
        return name != null && !name.trim().isEmpty()
            && price != null && price.compareTo(BigDecimal.ZERO) >= 0
            && stock != null && stock >= 0;
    }
    
    /**
     * Verifica si el producto está disponible (stock > 0)
     */
    public boolean isAvailable(Integer stock) {
        return stock != null && stock > 0;
    }
    
    /**
     * Reduce el stock del producto
     */
    public void reduceStock(int quantity) {
        if (stock == null || stock < quantity) {
            throw new IllegalStateException("Insufficient stock");
        }
        this.stock -= quantity;
    }
    
    /**
     * Aumenta el stock del producto
     */
    public void increaseStock(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        this.stock = (this.stock == null ? 0 : this.stock) + quantity;
    }

    public void setCreatedByUser(User user) {
        this.createdBy = user.getId();
    }
}

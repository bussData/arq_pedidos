package com.tecsup.app.micro.catalog.infraestructure.persistence.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "restaurants", indexes = {
        @Index(name = "idx_restaurants_name", columnList = "name"),
        @Index(name = "idx_restaurants_enabled", columnList = "enabled")
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String type;

    @Column(columnDefinition = "TEXT")
    private String address;

    @Builder.Default
    @Column(nullable = false)
    private Boolean enabled = true;

    @PrePersist
    protected void onCreate() {
      //insertar la secuencia de bd
        enabled = true;
    }
}

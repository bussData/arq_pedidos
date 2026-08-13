package com.tecsup.app.micro.catalog.infraestructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categories")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "categories_generator"
    )
    @SequenceGenerator(
            name = "categories_generator",
            sequenceName = "seq_categories",
            allocationSize = 1
    )
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;
}

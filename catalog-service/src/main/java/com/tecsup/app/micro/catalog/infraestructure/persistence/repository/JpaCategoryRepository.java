package com.tecsup.app.micro.catalog.infraestructure.persistence.repository;

import com.tecsup.app.micro.catalog.domain.model.Category;
import com.tecsup.app.micro.catalog.infraestructure.persistence.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaCategoryRepository extends JpaRepository<CategoryEntity, Long> {
    CategoryEntity findByName(String name);

}

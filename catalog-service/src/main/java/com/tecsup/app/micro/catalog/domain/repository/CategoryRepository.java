package com.tecsup.app.micro.catalog.domain.repository;

import com.tecsup.app.micro.catalog.domain.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    Optional<Category> findById(Long id);

    List<Category> findAllById(List<Long> ids);
}

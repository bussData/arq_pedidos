package com.tecsup.app.micro.catalog.infraestructure.persistence.repository;

import com.tecsup.app.micro.catalog.domain.model.Category;
import com.tecsup.app.micro.catalog.domain.repository.CategoryRepository;
import com.tecsup.app.micro.catalog.infraestructure.persistence.entity.CategoryEntity;
import com.tecsup.app.micro.catalog.infraestructure.persistence.mapper.CatalogoPersistenceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CategoryRepositoryImpl implements CategoryRepository {

    private final JpaCategoryRepository jpaCategoryRepository;
    private final CatalogoPersistenceMapper mapper;


    @Override
    public Optional<Category> findById(Long id) {
        return jpaCategoryRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<Category> findAllById(List<Long> ids) {
        List<CategoryEntity> entities =
                jpaCategoryRepository.findAllById(ids);

        return mapper.toDomainList(entities);
    }
}

package com.tecsup.app.micro.catalog.infraestructure.persistence.mapper;

import com.tecsup.app.micro.catalog.domain.model.Category;
import com.tecsup.app.micro.catalog.infraestructure.persistence.entity.CategoryEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CatalogoPersistenceMapper {
    Category toDomain(CategoryEntity categoryEntity);

    List<Category> toDomainList(List<CategoryEntity> allById);
}

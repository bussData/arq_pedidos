package com.tecsup.app.micro.catalog.infraestructure.web.mapper;

import com.tecsup.app.micro.catalog.domain.model.Product;

import com.tecsup.app.micro.catalog.infraestructure.web.dto.ProductResponse;
import com.tecsup.app.micro.catalog.infraestructure.web.dto.UpdateProductRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Mapper entre DTOs de presentación y modelo de dominio usando MapStruct
 */
@Mapper(componentModel = "spring")
public interface ProductDtoMapper {
    
       /**
     * Convierte UpdateProductRequest a Product de dominio
     */
    Product toDomain(UpdateProductRequest request);

    ProductResponse toResponse(Product updatedProduct);
}

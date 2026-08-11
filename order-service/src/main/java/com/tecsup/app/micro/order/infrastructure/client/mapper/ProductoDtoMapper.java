package com.tecsup.app.micro.order.infrastructure.client.mapper;

import com.tecsup.app.micro.order.domain.model.Product;
import com.tecsup.app.micro.order.infrastructure.client.dto.ProductoDTO;
import org.mapstruct.Mapper;
import org.springframework.http.ResponseEntity;

@Mapper(componentModel = "spring")
public interface ProductoDtoMapper {

    //ProductoDTO toDomain(ProductoDTO productoDTO);

    Product toDomain(ProductoDTO productoDTO);

    ProductoDTO toDto(Product producto);
}

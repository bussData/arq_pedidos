package com.tecsup.app.micro.catalog.infraestructure.client.mapper;


import com.tecsup.app.micro.catalog.domain.model.User;
import com.tecsup.app.micro.catalog.infraestructure.client.dto.UserDTO;
import com.tecsup.app.micro.catalog.infraestructure.web.dto.UserResponse;
import org.mapstruct.Mapper;

/**
 * Mapper entre entidades JPA y modelo de dominio usando MapStruct
 */
@Mapper(componentModel = "spring")
public interface UserDtoMapper {
    
    User toDomain(UserDTO dto);

    UserResponse toResponse(User user);

}

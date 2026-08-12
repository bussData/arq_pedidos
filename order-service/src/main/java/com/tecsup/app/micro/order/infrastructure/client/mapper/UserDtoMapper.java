package com.tecsup.app.micro.order.infrastructure.client.mapper;

import com.tecsup.app.micro.order.domain.model.User;
import com.tecsup.app.micro.order.infrastructure.client.dto.UserDTO;
import org.mapstruct.Mapper;
import org.springframework.http.ResponseEntity;

/**
 * Mapper entre DTOs de presentación y modelo de dominio usando MapStruct
 */
@Mapper(componentModel = "spring")
public interface UserDtoMapper {

    UserDTO toDomain(UserDTO body);
    UserDTO toDto(User user);
    User toDomain(ResponseEntity<UserDTO> response);
    User toDomain2(UserDTO userDTO);
}
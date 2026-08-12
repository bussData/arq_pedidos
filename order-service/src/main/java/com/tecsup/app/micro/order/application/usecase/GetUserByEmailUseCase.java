package com.tecsup.app.micro.order.application.usecase;

import com.tecsup.app.micro.order.domain.exception.InvalidOrderDataException;
import com.tecsup.app.micro.order.domain.model.User;
import com.tecsup.app.micro.order.infrastructure.client.UserClient;
import com.tecsup.app.micro.order.infrastructure.client.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetUserByEmailUseCase {

    private final UserClient userClient;

    public User execute(String email, String token) {

        log.debug("Inicio busqueda de user por email:",email);

        try{
            User cliente = userClient.getUserByEmail(email, token.replace("Bearer ",""));

            return cliente;
        }catch (Exception e) {
            throw new InvalidOrderDataException("Cliente con el email " + email + " no encontrado");
        }
    }

}

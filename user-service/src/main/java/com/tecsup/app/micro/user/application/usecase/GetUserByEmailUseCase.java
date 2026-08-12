package com.tecsup.app.micro.user.application.usecase;

import com.tecsup.app.micro.user.domain.exception.UserNotFoundException;
import com.tecsup.app.micro.user.domain.model.User;
import com.tecsup.app.micro.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetUserByEmailUseCase {

    private final UserRepository userRepository;

    public User execute(String email) {
        log.debug("Executing GetUserByEmailUseCase for email: {}", email);
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }
}

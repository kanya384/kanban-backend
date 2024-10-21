package com.laurkan.kanban.utils;

import com.laurkan.kanban.entity.User;
import com.laurkan.kanban.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserUtils {
    private final UserRepository userRepository;

    public User getCurrentUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        var email = authentication.getName();
        Optional<User> maybeUser = userRepository.findByEmail(email);
        return maybeUser.orElse(null);

    }
}

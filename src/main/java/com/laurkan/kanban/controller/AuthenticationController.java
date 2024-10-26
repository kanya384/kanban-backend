package com.laurkan.kanban.controller;

import com.laurkan.kanban.dto.AuthRequest;
import com.laurkan.kanban.utils.JWTUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
@Tag(name = "Аутентификация", description = "Аутентификация пользователей")
public class AuthenticationController {
    private final JWTUtils jwtUtils;

    private final AuthenticationManager authenticationManager;

    @PostMapping
    @Operation(
            summary = "Аутентификация пользователя",
            description = "Позволяет аутентифицировать пользователя"
    )
    public String create(@RequestBody AuthRequest authRequest) {
        var authentication = new UsernamePasswordAuthenticationToken(
                authRequest.getUsername(), authRequest.getPassword()
        );

        authenticationManager.authenticate(authentication);

        return jwtUtils.generateToken(authRequest.getUsername());
    }
}

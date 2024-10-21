package com.laurkan.kanban.mapper;

import com.laurkan.kanban.dto.UserCreateDTO;
import com.laurkan.kanban.dto.UserDTO;
import com.laurkan.kanban.entity.User;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class UserMapper {
    @Autowired
    PasswordEncoder passwordEncoder;

    public abstract User map(UserCreateDTO dto);

    public abstract UserDTO map(User model);

    @BeforeMapping
    public void encryptPassword(UserCreateDTO data) {
        var password = data.getPasswordDigest();
        data.setPasswordDigest(passwordEncoder.encode(password));
    }
}

package com.todolist.dto.response;

import com.todolist.constants.Constants;
import com.todolist.domain.Role;
import com.todolist.domain.User;
import com.todolist.dto.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserResponseDto extends BaseDto {
    private String username;
    private CategoryResponseDto category;
    private Set<Role> roles;

    public static UserResponseDto of(User entity) {
        return toDto(entity);
    }

    public static User toEntity(UserResponseDto dto) {
        return Constants.mapper().convertValue(dto, User.class);
    }

    public static UserResponseDto toDto(User entity) {
        return Constants.mapper().convertValue(entity, UserResponseDto.class);
    }
}

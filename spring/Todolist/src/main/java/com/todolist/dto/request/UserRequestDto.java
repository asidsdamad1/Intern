package com.todolist.dto.request;

import com.todolist.constants.Constants;
import com.todolist.domain.User;
import com.todolist.dto.BaseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
public class UserRequestDto extends BaseDto {
    private String username;
    private String password;

    public static User toEntity(UserRequestDto dto) {
        return Constants.mapper().convertValue(dto, User.class);
    }

    public static UserRequestDto toDto(User entity) {
        return Constants.mapper().convertValue(entity, UserRequestDto.class);
    }

    public UserRequestDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

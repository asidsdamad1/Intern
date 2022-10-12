package com.todolist.dto.request;

import com.todolist.constants.Constants;
import com.todolist.domain.User;
import com.todolist.dto.BaseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRequestDto extends BaseDto {
    private String username;
    private String password;

    public static User toEntity(UserRequestDto dto) {
        return Constants.mapper().convertValue(dto, User.class);
    }

    public UserRequestDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

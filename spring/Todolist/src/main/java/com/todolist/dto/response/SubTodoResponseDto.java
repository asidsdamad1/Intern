package com.todolist.dto.response;

import com.todolist.constants.Constants;
import com.todolist.domain.SubTodo;
import com.todolist.dto.BaseDto;
import com.todolist.dto.request.SubTodoRequestDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubTodoResponseDto extends BaseDto {
    private String title;
    private String notes;
    private boolean done;

    private TodoResponseDto parent;

    public static SubTodoResponseDto of(SubTodo subTodo) {
        return toDto(subTodo);
    }

    public SubTodo toEntity(SubTodoRequestDto todoRequestDto) {
        return Constants.mapper().convertValue(todoRequestDto, SubTodo.class);
    }

    public static SubTodoResponseDto toDto(SubTodo entity) {
        return Constants.mapper().convertValue(entity, SubTodoResponseDto.class);
    }
}

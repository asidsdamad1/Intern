package com.todolist.dto.response;

import com.todolist.constants.Constants;
import com.todolist.domain.Todo;
import com.todolist.dto.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TodoResponseDto extends BaseDto {
    private String title;
    private String notes;
    private boolean done;

    private CategoryResponseDto category;
    private List<SubTodoResponseDto> subTodos;

    public static TodoResponseDto of(Todo todo) {
        return toDto(todo);
    }

    public static Todo toEntity(TodoResponseDto dto) {
        return Constants.mapper().convertValue(dto, Todo.class);
    }

    public static TodoResponseDto toDto(Todo entity) {
        return Constants.mapper().convertValue(entity, TodoResponseDto.class);
    }


}

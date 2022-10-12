package com.todolist.dto.request;

import com.todolist.constants.Constants;
import com.todolist.domain.Todo;
import com.todolist.dto.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoRequestDto extends BaseDto {
    private String title;
    private String notes;
    private boolean done;

    private CategoryRequestDto category;

    public static Todo toEntity(TodoRequestDto todoRequestDto) {
        return Constants.mapper().convertValue(todoRequestDto, Todo.class);
    }

}

package com.todolist.dto.request;

import com.todolist.constants.Constants;
import com.todolist.domain.SubTodo;
import com.todolist.dto.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubTodoRequestDto extends BaseDto {
    private String title;
    private String notes;
    private boolean done;

    private TodoRequestDto parent;

    public static SubTodo toEntity(SubTodoRequestDto todoRequestDto) {
        return Constants.mapper().convertValue(todoRequestDto, SubTodo.class);
    }


}

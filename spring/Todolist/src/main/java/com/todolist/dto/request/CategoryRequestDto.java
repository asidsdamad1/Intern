package com.todolist.dto.request;

import com.todolist.constants.Constants;
import com.todolist.domain.Category;
import com.todolist.dto.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequestDto extends BaseDto {
    private String name;
    private String description;
    private UserRequestDto user;

    public static Category toEntity(CategoryRequestDto dto) {
        return Constants.mapper().convertValue(dto, Category.class);
    }
}

package com.todolist.dto.response;

import com.todolist.constants.Constants;
import com.todolist.domain.Category;
import com.todolist.dto.BaseDto;
import com.todolist.dto.request.UserRequestDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryResponseDto extends BaseDto {
    private String name;
    private String description;
    private UserRequestDto user;
    private List<TodoResponseDto> todos;

    public static CategoryResponseDto of(Category category) {
        return toDto(category);
    }

    public static Category toEntity(CategoryResponseDto dto) {
        return Constants.mapper().convertValue(dto, Category.class);
    }

    public static CategoryResponseDto toDto(Category entity) {
        return Constants.mapper().convertValue(entity, CategoryResponseDto.class);
    }
}

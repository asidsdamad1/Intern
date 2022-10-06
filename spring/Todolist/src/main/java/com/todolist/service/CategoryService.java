package com.todolist.service;

import com.todolist.dto.request.CategoryRequestDto;
import com.todolist.dto.response.CategoryResponseDto;
import com.todolist.dto.response.UserResponseDto;
import org.springframework.data.domain.Page;

public interface CategoryService {
    CategoryResponseDto save(CategoryRequestDto dto);

    CategoryResponseDto update(CategoryRequestDto dto, Long id);

    CategoryResponseDto getById(Long id);

    Page<CategoryResponseDto> getAll(int page, int size, String[] sorts, UserResponseDto userResponseDto);

    Boolean deleteById(Long id);
}

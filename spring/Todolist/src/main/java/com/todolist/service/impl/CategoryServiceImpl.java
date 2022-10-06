package com.todolist.service.impl;

import com.todolist.domain.Category;
import com.todolist.domain.Todo;
import com.todolist.dto.request.CategoryRequestDto;
import com.todolist.dto.request.UserRequestDto;
import com.todolist.dto.response.CategoryResponseDto;
import com.todolist.dto.response.UserResponseDto;
import com.todolist.exception.request.BadRequestException;
import com.todolist.exception.request.NotFoundException;
import com.todolist.repository.CategoryRepository;
import com.todolist.repository.TodoRepository;
import com.todolist.service.CategoryService;
import com.todolist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final TodoRepository todoRepository;
    private final UserService userService;

    @Override
    public CategoryResponseDto save(CategoryRequestDto dto) {
        if (dto != null) {
            Category category = Category.builder()
                    .name(dto.getName())
                    .description(dto.getDescription())
                    .user(UserResponseDto.toEntity(userService.getCurrentUser()))
                    .build();
            return CategoryResponseDto.toDto(categoryRepository.save(category));
        }
        return null;
    }

    @Override
    public CategoryResponseDto update(CategoryRequestDto dto, Long id) {
        if (id == null) {
            throw new BadRequestException("Category Id is null");
        }

        CategoryResponseDto categoryResponseDto = getById(id);

        if (dto != null) {
            List<Todo> todos = todoRepository.getByCategoryId(id);

            Category category = Category.builder()
                    .name(dto.getName())
                    .description(dto.getDescription())
                    .user(UserRequestDto.toEntity(dto.getUser()))
                    .todos(todos)
                    .build();
            category.setId(categoryResponseDto.getId());

            return CategoryResponseDto.toDto(categoryRepository.save(category));
        }
        return null;
    }

    @Override
    public CategoryResponseDto getById(Long id) {
        return categoryRepository.findById(id)
                .map(CategoryResponseDto::of)
                .orElseThrow(() ->
                {
                    throw new NotFoundException("category id not found");
                });
    }

    @Override
    public Boolean deleteById(Long id) {
        if (id != null) {
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Page<CategoryResponseDto> getAll(int page, int size, String[] sorts, UserResponseDto userRequestDto) {
        List<Sort.Order> orders = new ArrayList<>();

        if (sorts[0].contains(",")) {
            //sort more than 2 fields, sortOrder="field, direction"
            for (String sortOrder : sorts) {
                String[] sort = sortOrder.split(",");
                orders.add(new Sort.Order(Sort.Direction.valueOf(sort[1]), sort[0]));
            }
        } else {
            orders.add(new Sort.Order(Sort.Direction.valueOf(sorts[1]), sorts[0]));
        }
        Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));
        Page<Category> pageCategory = categoryRepository.findAll(userRequestDto.getId(), pagingSort);

        List<CategoryResponseDto> todoResponses = new ArrayList<>();
        for (Category todo : pageCategory.getContent()) {
            todoResponses.add(CategoryResponseDto.toDto(todo));
        }

        return new PageImpl<>(todoResponses);
    }
}

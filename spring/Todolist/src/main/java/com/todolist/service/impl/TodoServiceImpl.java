package com.todolist.service.impl;

import com.todolist.domain.SubTodo;
import com.todolist.domain.Todo;
import com.todolist.dto.request.CategoryRequestDto;
import com.todolist.dto.request.TodoRequestDto;
import com.todolist.dto.response.TodoResponseDto;
import com.todolist.dto.response.UserResponseDto;
import com.todolist.exception.request.BadRequestException;
import com.todolist.repository.SubTodoRepository;
import com.todolist.repository.TodoRepository;
import com.todolist.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;
    private final SubTodoRepository subTodoRepository;

    @Override
    public TodoResponseDto save(TodoRequestDto dto) {
        if (dto != null) {
            Todo todo = Todo.builder()
                    .notes(dto.getNotes())
                    .title(dto.getTitle())
                    .done(dto.isDone())
                    .category(CategoryRequestDto.toEntity(dto.getCategory()))
                    .build();
            return TodoResponseDto.toDto(todoRepository.save(todo));
        }
        return null;
    }

    @Override
    public TodoResponseDto update(TodoRequestDto dto, Long id) {
        if (id == null) {
            throw new BadRequestException("Todo Id is null");
        }
        TodoResponseDto responseDto = getById(id);

        if (dto != null) {
            List<SubTodo> subTodos = subTodoRepository.getByParentId(id);
            Todo todo = Todo.builder()
                    .notes(dto.getNotes())
                    .title(dto.getTitle())
                    .done(dto.isDone())
                    .category(CategoryRequestDto.toEntity(dto.getCategory()))
                    .subTodos(subTodos)
                    .build();
            todo.setId(responseDto.getId());

            return TodoResponseDto.toDto(todoRepository.save(todo));
        }
        return null;
    }

    @Override
    public TodoResponseDto getById(Long id) {
        return todoRepository.findById(id)
                .map(TodoResponseDto::of)
                .orElseThrow(() ->
                {
                    throw new BadRequestException("Cannot find Todo with id " + id);
                });
    }

    @Override
    public Boolean deleteById(Long id) {
        // get db
        if (id == null) {
            throw new BadRequestException("Category Id is null");
        }
        // throw EntityNotfoundNotFound
        TodoResponseDto responseDto = getById(id);
        if (responseDto != null) {
            todoRepository.delete(TodoResponseDto.toEntity(responseDto));
            return true;
        }
        return false;
    }

    @Override
    public Page<TodoResponseDto> getAll(int page, int size, String[] sorts, UserResponseDto userRequestDto) {
        List<Order> orders = new ArrayList<>();

        if (sorts[0].contains(",")) {
            //sort more than 2 fields, sortOrder="field, direction"
            for (String sortOrder : sorts) {
                String[] sort = sortOrder.split(",");
                orders.add(new Order(Sort.Direction.valueOf(sort[1]), sort[0]));
            }
        } else {
            orders.add(new Order(Sort.Direction.valueOf(sorts[1]), sorts[0]));
        }
        Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));
        Page<Todo> pageTodos = todoRepository.findAll(userRequestDto.getId(), pagingSort);

        List<TodoResponseDto> todoResponses = new ArrayList<>();
        for (Todo todo : pageTodos.getContent()) {
            todoResponses.add(TodoResponseDto.toDto(todo));
        }

        return new PageImpl<>(todoResponses);

    }
}

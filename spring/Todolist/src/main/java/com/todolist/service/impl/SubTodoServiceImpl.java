package com.todolist.service.impl;

import com.todolist.domain.SubTodo;
import com.todolist.dto.request.SubTodoRequestDto;
import com.todolist.dto.response.SubTodoResponseDto;
import com.todolist.dto.response.UserResponseDto;
import com.todolist.exception.request.BadRequestException;
import com.todolist.repository.SubTodoRepository;
import com.todolist.service.SubTodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubTodoServiceImpl implements SubTodoService {
    private final SubTodoRepository subTodoRepository;

    @Override
    public SubTodoResponseDto save(SubTodoRequestDto dto) {
        if (dto != null) {
            SubTodo entity = SubTodoRequestDto.toEntity(dto);
            return SubTodoResponseDto.toDto(subTodoRepository.save(entity));
        }
        return null;
    }

    @Override
    public SubTodoResponseDto update(SubTodoRequestDto dto, Long id) {
        if (id == null) {
            throw new BadRequestException("Sub Todo Id is null");
        }
        SubTodoResponseDto responseDto = getById(id);

        if (dto != null) {
            SubTodo subTodo = SubTodo.builder()
                    .notes(dto.getNotes())
                    .title(dto.getTitle())
                    .done(dto.isDone())
                    .build();
            subTodo.setId(responseDto.getId());

            return SubTodoResponseDto.toDto(subTodoRepository.save(subTodo));
        }
        return null;
    }

    @Override
    public SubTodoResponseDto getById(Long id) {
        return subTodoRepository.findById(id)
                .map(SubTodoResponseDto::of)
                .orElseThrow(() ->
                {
                    throw new BadRequestException(String.format("Cannot find Todo with id %s", id));
                });
    }

    @Override
    public Boolean deleteById(Long id) {
        // get db
        if (id == null) {
            throw new BadRequestException("Category Id is null");
        }
        // throw EntityNotfoundNotFound
        SubTodoResponseDto responseDto = getById(id);
        if (responseDto != null) {
            subTodoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Page<SubTodoResponseDto> getAll(int page, int size, String[] sorts, UserResponseDto userRequestDto) {
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
        Page<SubTodo> pageTodos = subTodoRepository.findAll(userRequestDto.getId(), pagingSort);

        List<SubTodoResponseDto> todoResponses = pageTodos.getContent().stream()
                .map(SubTodoResponseDto::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(todoResponses);

    }
}

package com.todolist.rest;

import com.todolist.dto.request.TodoRequestDto;
import com.todolist.dto.response.TodoResponseDto;
import com.todolist.service.TodoService;
import com.todolist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todo")
public class RestTodoController {
    private final TodoService service;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<TodoResponseDto> save(@RequestBody TodoRequestDto dto) {
        return ResponseEntity.ok(service.save(dto));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TodoResponseDto> update(@RequestBody TodoRequestDto dto, @PathVariable Long id) {
        return ResponseEntity.ok(service.update(dto, id));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TodoResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteById(id));
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<Page<TodoResponseDto>> getByPage(
            @RequestParam(defaultValue = "0") int pageIndex,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(defaultValue = "id,ASC") String[] sorts
    ) {
        return ResponseEntity.ok(service.getAll(pageIndex, pageSize, sorts, userService.getCurrentUser()));

    }
}

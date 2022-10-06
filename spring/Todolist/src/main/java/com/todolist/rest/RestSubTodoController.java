package com.todolist.rest;


import com.todolist.dto.request.SubTodoRequestDto;
import com.todolist.dto.response.SubTodoResponseDto;
import com.todolist.service.SubTodoService;
import com.todolist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subtodo")
public class RestSubTodoController {
    private final SubTodoService service;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<SubTodoResponseDto> save(@RequestBody SubTodoRequestDto dto) {
        return ResponseEntity.ok(service.save(dto));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<SubTodoResponseDto> update(@RequestBody SubTodoRequestDto dto, @PathVariable Long id) {
        return ResponseEntity.ok(service.update(dto, id));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<SubTodoResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteById(id));
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<Page<SubTodoResponseDto>> getByPage(
            @RequestParam(defaultValue = "0") int pageIndex,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(defaultValue = "id,ASC") String[] sorts
    ) {
        return ResponseEntity.ok(service.getAll(pageIndex, pageSize, sorts, userService.getCurrentUser()));
    }
}

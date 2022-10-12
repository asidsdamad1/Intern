package com.todolist.rest;

import com.todolist.dto.request.CategoryRequestDto;
import com.todolist.dto.response.CategoryResponseDto;
import com.todolist.service.CategoryService;
import com.todolist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class RestCategoryController {
    private final CategoryService service;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<CategoryResponseDto> save(@RequestBody CategoryRequestDto dto) {
        return ResponseEntity.ok(service.save(dto));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoryResponseDto> update(@RequestBody CategoryRequestDto dto, @PathVariable Long id) {
        return ResponseEntity.ok(service.update(dto, id));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteById(id));
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<Page<CategoryResponseDto>> getByPage(
            @RequestParam(defaultValue = "0") int pageIndex,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(defaultValue = "id,ASC") String[] sorts
    ) {
        return ResponseEntity.ok(service.getAll(pageIndex, pageSize, sorts, userService.getCurrentUser()));
    }

}

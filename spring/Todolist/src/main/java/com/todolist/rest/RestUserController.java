package com.todolist.rest;

import com.todolist.dto.RoleToUserDto;
import com.todolist.dto.request.UserRequestDto;
import com.todolist.dto.response.UserResponseDto;
import com.todolist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class RestUserController {
    private final UserService service;

    @GetMapping(value = "/users")
    public ResponseEntity<Page<UserResponseDto>> getAll(
            @RequestParam(defaultValue = "0") int pageIndex,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(defaultValue = "id,ASC") String[] sorts
    ) {
        return ResponseEntity.ok(service.getAll(pageIndex, pageSize, sorts));
    }


    @PostMapping(value = "/user/save")
    public ResponseEntity<com.todolist.dto.response.UserResponseDto> saveUser(@RequestBody UserRequestDto dto) {
        return ResponseEntity.ok(service.saveUser(dto));
    }

    @PutMapping(value = "/user/{id}")
    public ResponseEntity<UserResponseDto> update(@RequestBody UserRequestDto dto, @PathVariable Long id) {
        return ResponseEntity.ok().body(service.updateUser(dto, id));
    }

    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteUser(id));
    }


    @PostMapping(value = "/role/addtouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserDto dto) {
        service.addRoleToUser(dto.getUsername(), dto.getRoleName());
        return ResponseEntity.ok().build();
    }

}

package com.todolist.rest;

import com.todolist.domain.Role;
import com.todolist.dto.RoleToUserDto;
import com.todolist.dto.UserDto;
import com.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestUserController {
    @Autowired
    private UserService userService;


    @RequestMapping(value = "/users", method = RequestMethod.GET)
    private ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @RequestMapping(value = "/users/save", method = RequestMethod.POST)
    private ResponseEntity<UserDto> saveUser(@RequestBody UserDto dto) {
        UserDto result = userService.saveUser(dto);
        return new ResponseEntity<UserDto>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/role/save", method = RequestMethod.POST)
    private ResponseEntity<Role> saveRole(@RequestBody Role role) {
        Role result = userService.saveRole(role);
        return new ResponseEntity<Role>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/role/addtouser", method = RequestMethod.POST)
    private ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserDto dto) {
        userService.addRoleToUser(dto.getUsername(), dto.getRoleName());
        return ResponseEntity.ok().build();
    }

}

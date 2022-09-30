package com.todolist.rest;

import com.todolist.dto.TodoDto;
import com.todolist.service.TodoService;
import com.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class RestTodoController {
    @Autowired
    TodoService service;
    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<TodoDto> save(@RequestBody TodoDto dto) {
        TodoDto result = service.saveOrUpdate(dto, null);
        return new ResponseEntity<TodoDto>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<TodoDto> update(@RequestBody TodoDto dto, @PathVariable Long id) {
        TodoDto result = service.saveOrUpdate(dto, id);
        return new ResponseEntity<TodoDto>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<TodoDto> getById(@PathVariable Long id) {
        TodoDto result = service.getById(id);
        return new ResponseEntity<TodoDto>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id) {
        Boolean result = service.deleteById(id);
        return new ResponseEntity<Boolean>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<List<TodoDto>> getAll() {
        List<TodoDto> result = service.getAll(userService.getCurrentUser());
        return new ResponseEntity<List<TodoDto>>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}

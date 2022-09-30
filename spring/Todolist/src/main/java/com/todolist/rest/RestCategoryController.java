package com.todolist.rest;

import com.todolist.dto.CategoryDto;
import com.todolist.service.CategoryService;
import com.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class RestCategoryController {
    @Autowired
    CategoryService service;
    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CategoryDto> save(@RequestBody CategoryDto dto) {
        CategoryDto result = service.saveOrUpdate(dto, null);
        return new ResponseEntity<CategoryDto>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<CategoryDto> update(@RequestBody CategoryDto dto, @PathVariable Long id) {
        CategoryDto result = service.saveOrUpdate(dto, id);
        return new ResponseEntity<CategoryDto>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<CategoryDto> getById(@PathVariable Long id) {
        CategoryDto result = service.getById(id);
        return new ResponseEntity<CategoryDto>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id) {
        Boolean result = service.deleteById(id);
        return new ResponseEntity<Boolean>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<List<CategoryDto>> getAll() {
        List<CategoryDto> result = service.getByUser(userService.getCurrentUser());
        return new ResponseEntity<List<CategoryDto>>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

}

package com.todolist.rest;

import com.todolist.dto.CategoryDto;
import com.todolist.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
public class RestCategoryController {
    @Autowired
    CategoryService service;

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

}

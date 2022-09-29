package com.todolist.repository;

import com.todolist.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    @Query("select e from Todo e where e.id = ?1")
    Todo getById(Long id);

    @Query("select e from Todo e where e.parent.id = ?1")
    List<Todo> getByParentId(Long parentId);

    @Query("select distinct e from Todo e join fetch e.todoItems")
    List<Todo> getAll();
}
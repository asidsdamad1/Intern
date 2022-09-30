package com.todolist.repository;

import com.todolist.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("select e from Category e where e.user.id=?1")
    List<Category> getByUserId(long userId);
}

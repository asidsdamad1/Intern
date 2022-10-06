package com.todolist.repository;

import com.todolist.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("select e from Category e where e.user.id=?1")
    List<Category> getByUserId(long userId);

    Optional<Category> findById(Long id);

    @Query("select e from Category e where e.user.id=?1")
    Page<Category> findAll(Long id, Pageable pageable);
}

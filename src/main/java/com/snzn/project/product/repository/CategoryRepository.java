package com.snzn.project.product.repository;

import com.snzn.project.product.repository.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByNameAndDeletedFalse(String name);

    List<Category> findByDeletedFalse();

}

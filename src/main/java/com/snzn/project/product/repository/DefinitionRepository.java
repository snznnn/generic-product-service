package com.snzn.project.product.repository;

import com.snzn.project.product.repository.entity.Definition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DefinitionRepository extends JpaRepository<Definition, Long> {

    Optional<Definition> findByNameAndDeletedFalse(String name);

    List<Definition> findByDeletedFalse();

}

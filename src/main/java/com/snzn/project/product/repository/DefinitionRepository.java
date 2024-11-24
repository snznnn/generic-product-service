package com.snzn.project.product.repository;

import com.snzn.project.product.repository.entity.Definition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefinitionRepository extends JpaRepository<Definition, Long> {

}

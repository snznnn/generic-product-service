package com.snzn.project.product.repository;

import com.snzn.project.product.repository.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

    Optional<Property> findByNameAndDeletedFalse(String name);

    List<Property> findByDeletedFalse();

}

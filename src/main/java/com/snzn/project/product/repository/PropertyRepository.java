package com.snzn.project.product.repository;

import com.snzn.project.product.repository.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Long> {

}

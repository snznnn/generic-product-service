package com.snzn.project.product.repository;

import com.snzn.project.product.repository.entity.PropertyValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyValueRepository extends JpaRepository<PropertyValue, Long> {

}

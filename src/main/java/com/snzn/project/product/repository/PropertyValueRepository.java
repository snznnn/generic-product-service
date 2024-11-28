package com.snzn.project.product.repository;

import com.snzn.project.product.repository.entity.Product;
import com.snzn.project.product.repository.entity.PropertyValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyValueRepository extends JpaRepository<PropertyValue, Long> {

    List<PropertyValue> findByProduct(Product product);

    void deleteAllByProduct(Product productId);

}

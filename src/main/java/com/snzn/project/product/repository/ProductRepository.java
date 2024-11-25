package com.snzn.project.product.repository;

import com.snzn.project.product.repository.entity.Definition;
import com.snzn.project.product.repository.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByIdAndDeletedFalse(Long productId);

    Optional<Product> findByDefinitionAndBrandAndModel(Definition definition, String brand, String model);

    List<Product> findByDeletedFalse();

    List<Product> findByDefinitionIdAndDeletedFalse(Long definitionId);

}

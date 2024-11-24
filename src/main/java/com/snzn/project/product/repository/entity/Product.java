package com.snzn.project.product.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"definition_id", "brand", "model"}))
@Entity
public class Product extends BaseEntity {

    @ManyToOne
    @JoinColumn
    private Definition definition;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

}

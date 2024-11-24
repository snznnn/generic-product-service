package com.snzn.project.product.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
public class Definition extends BaseEntity {

    @ManyToOne
    @JoinColumn(nullable = false)
    private Category category;

    @ManyToMany
    @JoinTable
    private List<Property> propertyList;

    @Column(nullable = false, unique = true, updatable = false)
    private String name;

}
